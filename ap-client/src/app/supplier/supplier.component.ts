import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {PromotionService} from '../services/index'
import { Promotion, PromoStatus } from "../models/index";
import { ModalService } from '../shared/modal-services/ModalService';
import { ModalDialogService } from 'ngx-modal-dialog';
import { AddPromotionComponent } from '../modal/add-promotion/add-promotion.component';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
  providers: [PromotionService]
})
export class SupplierComponent implements OnInit {

   promotion:Promotion;
   private promoStatus:PromoStatus;

  constructor(private promotionService:PromotionService, 
    private modalService: ModalService, 
    private modalDialogService: ModalDialogService, private viewContainer: ViewContainerRef) {}

  ngOnInit() {
      this.getSupplierPromotion(1,'2018-01-01');
  }

  getSupplierPromotion(id:Number,promoyear:String) {
    this.promotionService.getSupplierPromotions(id,promoyear).subscribe((sPromotion:Promotion) => {
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

  submitSupplierPromotion() {
    console.debug("promo year"+this.promotion.promoyear);
    this.promoStatus = new PromoStatus(this.promotion.userid,this.promotion.status,"SUBMITTED",this.promotion.promoyear)
    this.promotionService.submitSupplierPromotion(this.promoStatus).subscribe((response:PromoStatus) => {
      console.debug("POST submitSupplierPromotion Call Success");
      console.log(response);
    },
    error => { 
        console.error("ERROR! submitSupplierPromotion = "+error);
    });
  }

   editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.promotion.ratecards[rowid].dualmailers[id]);

    // Showing modal on cell click
    this.modalDialogService.openDialog(this.viewContainer ,{
      title: 'Dynamic Promotion',
      childComponent: AddPromotionComponent,
      settings: {
        closeButtonClass: 'close theme-icon-close',
      },
      data: {
        values : this.promotion.ratecards[rowid].dualmailers[id].value,
        brandAndProducts : this.promotion.mapOfProducts,
        parentRef : this.viewContainer
      }
    });

  }

  saveTable() {
  console.log(this.promotion);
  }

}
