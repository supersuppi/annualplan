import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../services/budget.service';
import { Promotion, PromotionBudget } from '../models';
import { Router } from '@angular/router';
import { ToastNotificationService } from '../services/toast-notification.service';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/forkJoin';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  private activePromotions:Array<any>;
  private promotionId:number = -1;
  private budgetAmount:number = 0;
  private supplierBudgets:Array<any>;

  constructor(private budgetService: BudgetService,
    private router: Router,private toast:ToastNotificationService) { }

  ngOnInit() {
    this.getBudgetAndPromoForSupplier();
  }

  /**
   * Select the promotion to add budget to.
   * @param event 
   */
  selectPromotion(event : Event) {
    this.promotionId = +event.target["value"];
    this.budgetService.getPromotionBudget(this.promotionId).subscribe(
      (data) => {
        this.budgetAmount = data["allocated"];
      }, (err) => {
        console.log("Something went wrong");
        this.toast.showError("Something went wrong!Try again");
      }
    );
  }

  /**
   * Mutliple requests are configured using 'forkJoin'
   */
  budgetAndActivePromoObservable() : Observable<any[]> {
    return Observable.forkJoin([this.budgetService.getAllActivePromotions(),
      this.budgetService.getAllBudgetForSupplier()]);
  }

  /**
   * The observable created is triggered to get list of promotions as 
   * well as all the budgets created by the supplier.
   */
  getBudgetAndPromoForSupplier() {
    this.budgetAndActivePromoObservable().subscribe(
      (data) => {
        this.activePromotions = data[0];
        this.supplierBudgets = data[1];
      }, (err) => {
        console.log(err);
        this.toast.showError("Something went wrong!Try again");
      }
    );
  }

  /**
   * Save the budget created by supplier for the promotion.
   */
  saveBudgetForSupplier() {
    console.log("save budget");
    this.budgetService.saveBudget(this.promotionId, this.budgetAmount).subscribe(
      () => {
        this.toast.showSuccess("Budget created.");
      },
      (err) => {
        console.log("Error occurred-saveBudgetForSupplier");
        this.toast.showError("Something went wrong!Try again");
      }
    );
  }

  /**
   * Clicking on cancel
   */
  returnToHome() {
    console.log("return to home");
    this.router.navigate(['home']);
  }

}
