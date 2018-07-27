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

  private isEditable:Boolean
  private rows:Array<RateCard>
  private products:Array<Product>
  private promotion:Promotion;

  constructor(private promotionService:PromotionService, 
    private modalService: ModalService) {}

  ngOnInit() {
      this.getSupplierPromotion(1);
  }

  clickMe() {
    console.log("clicked here");
    this.modalService.init(ProductSelectionModalComponent);
  }

  private getSupplierPromotion(id:Number) {
    this.promotionService.getSupplierPromotions(id).subscribe((sPromotion:Promotion) => {
        console.debug("Get SupplierPromotion Call Success");
        this.rows = sPromotion.ratecards;
        this.products = sPromotion.products;
      },
      error => { 
          console.error("ERROR! SupplierComponent:getSupplierPromotion = "+error);
      });
  }

  private editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.rows[rowid].dualmailers[id]);
  }

  private saveTable() {
    console.log(this.rows);
  }
}
