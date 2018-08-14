import { Component, OnInit, ViewContainerRef } from '@angular/core';

import { Promotion,PromoStatus,PromoComment } from "../models/index";
import { ModalDialogService } from 'ngx-modal-dialog';
import { PromotionRejectModalComponent } from '../modal/promotion-reject-modal/promotion-reject-modal.component';
import { ManagerPromotionService } from '../services/index';


@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss'],
  providers: [ManagerPromotionService]
})
export class ManagerComponent implements OnInit {

  private promotion:Promotion;
  private promoStatus:PromoStatus;
  private promoComment : PromoComment;

  constructor(private promotionService:ManagerPromotionService,private modalDialogService: ModalDialogService,
    private viewContainer: ViewContainerRef) { }

  ngOnInit() {
    this.getSupplierPromotion(1,'2018-01-01');
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
      this.promoStatus = new PromoStatus(this.promotion.userid,this.promotion.status,"ACCEPTED",this.promotion.promoyear)
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

      this.promoComment = new PromoComment(this.promotion.promoyear,1)

      this.modalDialogService.openDialog(this.viewContainer ,{
        title: 'Promotion Rejection Message',

        childComponent: PromotionRejectModalComponent,
        settings: {
          closeButtonClass: 'close theme-icon-close'
        },
        data: {
          promoCommentModel : this.promoComment
        }
      });
    }

   

}
