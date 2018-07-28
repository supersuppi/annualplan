import { Component, OnInit} from '@angular/core';

import {PromotionService} from '../services/index'
import { Promotion, RateCard, Product } from "../models/index";
import { ModalService } from '../shared/modal-services/ModalService';
import { ProductSelectionModalComponent } from '../modal/product-selection-modal/product-selection-modal.component';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
  providers: [PromotionService]
})
export class SupplierComponent implements OnInit {

   promotion:Promotion;

  constructor(private promotionService:PromotionService, 
    private modalService: ModalService) {}

  ngOnInit() {
      this.getSupplierPromotion(1);
  }

  clickMe() {
    console.log("clicked here");
    this.modalService.init(ProductSelectionModalComponent);
  }

   getSupplierPromotion(id:Number) {
    this.promotionService.getSupplierPromotions(id).subscribe((sPromotion:Promotion) => {
        console.debug("Get SupplierPromotion Call Success");
        this.promotion = sPromotion;
      },
      error => { 
          console.error("ERROR! SupplierComponent:getSupplierPromotion = "+error);
      });
  }

  saveSupplierPromotion() {
    this.promotionService.saveSupplierPromotions(this.promotion).subscribe((response:String) => {
      console.debug("POST saveSupplierPromotion Call Success");
      console.log(response);
    },
    error => { 
        console.error("ERROR! saveSupplierPromotion:getSupplierPromotion = "+error);
    });
  }

   editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.promotion.ratecards[rowid].dualmailers[id]);
  }

   saveTable() {
    console.log(this.promotion);
  }
}
