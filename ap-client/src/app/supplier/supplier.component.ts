import { Component, OnInit, ViewContainerRef} from '@angular/core';

import {PromotionService} from '../services/index'
import { Promotion, RateCard, Product } from "../models/index";
import { ModalService } from '../shared/modal-services/ModalService';
import { ProductSelectionModalComponent } from '../modal/product-selection-modal/product-selection-modal.component';
import {} from '';
import { ModalDialogService } from 'ngx-modal-dialog';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
  providers: [PromotionService]
})
export class SupplierComponent implements OnInit {

   promotion:Promotion;

  constructor(private promotionService:PromotionService, 
    private modalService: ModalService, 
    private modalDialogService: ModalDialogService, private viewContainer: ViewContainerRef) {}

  ngOnInit() {
      this.getSupplierPromotion(1,'2018-02-02');
  }

  clickMe() {

    let promotion1 = [
      {sku : 'SKU-10', name: 'Test 1'},
      {sku : 'SKU-10', name: 'Test 1'}
    ];

    this.modalDialogService.openDialog(this.viewContainer ,{
      title: 'Choose Promotion Type',
      childComponent: ProductSelectionModalComponent,
      settings: {
        closeButtonClass: 'close theme-icon-close'
      },
      data: {
        promotion : promotion1
      }
    });

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

   editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.promotion.ratecards[rowid].dualmailers[id]);
  }

   saveTable() {
    console.log(this.promotion);
  }
}
