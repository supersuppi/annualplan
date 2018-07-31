import { Component, OnInit } from '@angular/core';

import { Promotion,PromoStatus } from "../models/index";
import {PromotionService} from '../services/index'

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss'],
  providers: [PromotionService]
})
export class ManagerComponent implements OnInit {

  private promotion:Promotion;
  private promoStatus:PromoStatus

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
      this.promoStatus = new PromoStatus(this.promotion.userid,this.promotion.status,"ACCEPTED")
      this.promotionService.changePromotionStatus(this.promoStatus).subscribe((response:PromoStatus) => {
        console.debug("Get acceptPromotion Call Success");
        console.log(response.statusChangeSuccess);
      },
      error => { 
          console.error("ERROR! ManagerComponent:acceptPromotion = "+error);
      });
    }

    rejectPromotion() {
      console.debug("rejectPromotion");
      this.promoStatus = new PromoStatus(this.promotion.userid,this.promotion.status,"REJECTED")
      this.promotionService.changePromotionStatus(this.promoStatus).subscribe((response:PromoStatus) => {
        console.debug("Get rejectPromotion Call Success");
        console.log(response.statusChangeSuccess);
      },
      error => { 
          console.error("ERROR! ManagerComponent:rejectPromotion = "+error);
      });
    }

   

}
