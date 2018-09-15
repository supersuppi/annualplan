import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../services/budget.service';
import { Promotion } from '../models';
import { Router } from '@angular/router';

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
    private router: Router) { }

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
      }
    );
  }

  getActivePromotionsForSupplier() {
    this.budgetService.getAllActivePromotions().subscribe(
      (data) => {
        this.activePromotions = data;
      }
    );
  }

  saveBudgetForSupplier() {
    console.log("save budget");
    this.budgetService.saveBudget(this.promotionId, this.budgetAmount).subscribe(
      () => {
        this.router.navigate(['home']);
      },
      (err) => {
        console.log("Error occurred");
      }
    );
  }

  returnToHome() {
    console.log("return to home");
    this.router.navigate(['home']);
  }

}
