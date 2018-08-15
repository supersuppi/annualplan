import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  private activePromoYear:String;
  private supplierID:Number;
  private pageLoaded:Boolean;//to avoid promotion undefined error

  constructor(private promotionService:ManagerPromotionService,private modalDialogService: ModalDialogService,
    private viewContainer: ViewContainerRef,private _Activatedroute:ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.pageLoaded =false;
    this.activePromoYear = this._Activatedroute.snapshot.params['pyear'];
    this.supplierID = this._Activatedroute.snapshot.params['sid'];
    this.getSupplierPromotion(this.supplierID,this.activePromoYear);
   }

   getSupplierPromotion(id:Number,promoyear:String) {
    this.promotionService.getSupplierPromotionsForManager(id,promoyear).subscribe((sPromotion:Promotion) => {
        console.debug("Get SupplierPromotion Call Success");
        this.promotion = sPromotion;
        this.pageLoaded =true;
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

      this.promoComment = new PromoComment(this.promotion.promoyear,this.supplierID,1)

      this.modalDialogService.openDialog(this.viewContainer ,{
        title: 'Promotion Rejection Message',

        childComponent: PromotionRejectModalComponent,
        settings: {
          closeButtonClass: 'close theme-icon-close'
        },
        data: {
          promoCommentModel : this.promoComment
        },
        actionButtons: [
          {
            text: 'Send',
            buttonClass: 'btn btn-primary',
            onAction: () => this.sendMessage()
          },
          {
            text: 'Cancel',
            buttonClass: 'btn btn-danger',
            onAction: () => true
          }
        ]
      });
    }

    goBack() {
      this.router.navigate(['/home']);
    }

    sendMessage():boolean {
      console.log("Message Sent");
      
      this.promotionService.savePromotionRejectComment(this.promoComment).subscribe((response:any) => {
        console.debug("save PromotionRejectComment Call Success");
        console.log(response);
        this.goBack();
      },
      error => { 
          console.error("ERROR! PromotionRejectModalComponent:sendMessage = "+error);
      });
  
      return true;
    }  

}
