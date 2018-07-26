import { Component, OnInit} from '@angular/core';

import {PromotionService} from '../services/index'
import { Promotion } from "../models/index";
import { ModalService } from '../shared/modal-services/ModalService';
import { ProductSelectionModalComponent } from '../modal/product-selection-modal/product-selection-modal.component';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
  providers: [PromotionService]
})
export class SupplierComponent implements OnInit {

  rows:Array<Promotion>

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
    this.promotionService.getSupplierPromotions(id).subscribe((sPromotion:Promotion[]) => {
        console.debug("Get SupplierPromotion Call Success");
        this.rows = sPromotion;
      },
      error => { 
          console.error("ERROR! SupplierComponent:getSupplierPromotion = "+error);
      });
  }

  editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.rows[rowid].dualmailers[id]);
  }

  saveTable() {
    console.log(this.rows);
  }



}
