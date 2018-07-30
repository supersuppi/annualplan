import { Component, OnInit } from '@angular/core';

import { Promotion } from "../models/index";
import {PromotionService} from '../services/index'

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss'],
  providers: [PromotionService]
})
export class ManagerComponent implements OnInit {

  promotion:Promotion;

  constructor(private promotionService:PromotionService) { }

  ngOnInit() {
    this.getSupplierPromotion(1,'2018-02-02');
   }

   getSupplierPromotion(id:Number,promoyear:String) {
    this.promotionService.getSupplierPromotionsForManager(id,promoyear).subscribe((sPromotion:Promotion) => {
        console.debug("Get SupplierPromotion Call Success");
        this.promotion = sPromotion;
      },
      error => { 
          console.error("ERROR! ManagerComponent:getSupplierPromotion = "+error);
      });
    }

    acceptPromotion() {
      console.debug("acceptPromotion");
    }

    rejectPromotion() {
      console.debug("rejectPromotion");
    }

   

}
