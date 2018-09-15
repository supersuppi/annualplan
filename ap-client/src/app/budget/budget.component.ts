import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../services/budget.service';
import { Promotion } from '../models';
import { Router } from '@angular/router';
import { ToastNotificationService } from '../services/toast-notification.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  private activePromotions:Array<any>;
  private promotionId:number = -1;
  private budgetAmount:number = 0;

  constructor(private budgetService: BudgetService,
    private router: Router,private toast:ToastNotificationService) { }

  ngOnInit() {
    this.getActivePromotionsForSupplier();
  }

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

  getActivePromotionsForSupplier() {
    this.budgetService.getAllActivePromotions().subscribe((data) => {
      console.debug("Get getActivePromotionsForSupplier Call Success");
      this.activePromotions = data;
    },
    error => { 
        console.error("ERROR! BudgetComponent:getActivePromotionsForSupplier = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

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

  returnToHome() {
    console.log("return to home");
    this.router.navigate(['home']);
  }

}
