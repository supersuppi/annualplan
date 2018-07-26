import { Component, OnInit} from '@angular/core';

import {PromotionService} from '../services/index'
import { Promotion } from "../models/index";

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss'],
  providers: [PromotionService]
})
export class SupplierComponent implements OnInit {

  rows:Array<Promotion>

  constructor(private promotionService:PromotionService) {}

  ngOnInit() {
      this.getSupplierPromotion(1);
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
