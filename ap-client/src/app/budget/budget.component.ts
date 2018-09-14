import { Component, OnInit } from '@angular/core';
import { BudgetService } from '../services/budget.service';
import { Promotion } from '../models';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  private activePromotions:Array<any>;
  private promotionId:number = -1;
  private budgetAmount:number = 0;

  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
    this.getActivePromotionsForSupplier();
  }

  selectPromotion(event : Event) {
    this.promotionId = +event.target["value"];
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
      () => {},
      (err) => {
        console.log("Error occurred");
      }
    );
  }

  returnToHome() {
    console.log("return to home");
  }

}
