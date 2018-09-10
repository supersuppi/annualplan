import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Promotion,PromoStatus,PromoComment, SupplierHomeData, RateCard, DualMailer } from "../models/index";
import { ModalDialogService } from 'ngx-modal-dialog';
import { PromotionRejectModalComponent } from '../modal/promotion-reject-modal/promotion-reject-modal.component';
import { ManagerPromotionService, SupplierPromotionService } from '../services/index';
import { AddPromotionComponent } from '../modal/add-promotion/add-promotion.component';
import { SKULevelPromoData } from '../models/sku-product-details';
import { ToastNotificationService } from '../services/toast-notification.service';

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
  private activePromoID:Number;
  private currentSupplierID:Number;
  private currentSupplierName:String;
  private currentSupplier:SupplierHomeData;
  private suppliers:Array<SupplierHomeData>;
  public pageLoaded:Boolean;//to avoid promotion undefined error
  private productDMBudgetList:Array<CalculatedBudget>;
  //hover variables
  public skuPromoDataList:Array<SKULevelPromoData>;
  dualMailers:Array<DualMailer>;
  supplier:SupplierHomeData;
  promotionName:String;
  
  constructor(private spromotionService: SupplierPromotionService,private promotionService:ManagerPromotionService,private modalDialogService: ModalDialogService,
    private viewContainer: ViewContainerRef,private _Activatedroute:ActivatedRoute, private router: Router,private toast:ToastNotificationService) { }

  ngOnInit() {
    this.pageLoaded =false;
    this.suppliers = JSON.parse(localStorage.getItem('suppliers'))
    this.supplier = JSON.parse(localStorage.getItem('supplier'))
    this.activePromoID = Number(localStorage.getItem('promoID'));
    this.promotionName = localStorage.getItem('promoName');
    //Default Supplier
    let supplier:SupplierHomeData = this.supplier;
    this.currentSupplierID = supplier.supplierID;
    this.currentSupplierName = supplier.supplierName;
    this.getSupplierPromotion(this.currentSupplierID,this.activePromoID.toString());
  
   }

   initBudgetTableData(ratecards:Array<RateCard>) {
    this.productDMBudgetList = new Array();
    for (let ratecard of ratecards) {
     this.dualMailers = ratecard.dualmailers;
     for (let dm of this.dualMailers) {
       if(dm.value!=0) {
        this.createOrUpdateBudget(ratecard,dm)
       }
      }
   }
  }

   getSupplierPromotion(id:Number,promoID:String) {
    this.promotionService.getSupplierPromotionsForManager(id,promoID).subscribe((sPromotion:Promotion) => {
        console.debug("Get SupplierPromotion Call Success");
        this.promotion = sPromotion;
        this.pageLoaded =true;
        //Create budget table data
        this.initBudgetTableData(sPromotion.ratecards);
      },
      error => { 
          console.error("ERROR! ManagerComponent:getSupplierPromotion = "+JSON.stringify(error));
          this.toast.showError("Could not find promotion for supplier");
      });
    }

    acceptPromotion() {
      console.debug("acceptPromotion");
      this.promoStatus = new PromoStatus(this.activePromoID,this.promotion.userid,this.promotion.status,"ACCEPTED",this.promotion.promoyear)
      this.promotionService.changePromotionStatus(this.promoStatus).subscribe((response:PromoStatus) => {
        console.debug("Get acceptPromotion Call Success");
        console.log(response.statusChangeSuccess);
        this.toast.showSuccess("Promotion Accepted.This Supplier will be notified");
        this.refreshData();
      },
      error => { 
          console.error("ERROR! ManagerComponent:acceptPromotion = "+JSON.stringify(error));
          this.toast.showError("Something went wrong!Try again");
      });
    }

    rejectPromotion() {
      console.debug("rejectPromotion");

      this.promoComment = new PromoComment(this.activePromoID.toString(),this.currentSupplierID,1)

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

    onHover(rowId,dmId){
      let totalPromoCounts = this.promotion.ratecards[rowId].dualmailers[dmId].value;
      this.skuPromoDataList = new Array();
      for (let i = 1; i <= totalPromoCounts; i++) { 
        this.spromotionService.getSelectedProducts(this.currentSupplierID,this.activePromoID, dmId, rowId, i)
        .subscribe( (data) => {
          let skudata = new SKULevelPromoData();
          skudata.productsSelected = data["products_selected"];
          skudata.promoName = data["promoName"];
          skudata.promoType = data["promoType"];
          this.skuPromoDataList.push(skudata);
        }, err => {
          console.log("Something went wrong");
        });
        } 
    }
  
    onHoverLeave() {
      this.skuPromoDataList = new Array();
    }

    refreshData() {
      this.getSupplierPromotion(this.currentSupplierID,this.activePromoID.toString());
    }

    onSelect(sID) {
      let itemIndex = this.suppliers.findIndex(item => item.supplierID == sID);
      if(itemIndex === -1){
        console.log("supplier not found in list");
      } else {
        let supObj = this.suppliers[itemIndex];
        this.currentSupplierName = supObj.supplierName;
        this.currentSupplierID = supObj.supplierID;
        this.getSupplierPromotion(supObj.supplierID,this.activePromoID.toString());
      }
    }

    sendMessage():boolean {
      console.log("Message Sent");
      
      this.promotionService.savePromotionRejectComment(this.promoComment).subscribe((response:any) => {
        console.debug("save PromotionRejectComment Call Success");
        console.log(response);
        this.toast.showSuccess("Promotion rejected! This Supplier will be notified");
        this.refreshData();
      },
      error => { 
          console.error("ERROR! PromotionRejectModalComponent:sendMessage = "+error);
          this.toast.showError("Something went wrong!Try again");
      });
  
      return true;
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
          rowId : rowid,
          dmId : id,
          brandAndProducts : this.promotion.products,
          parentRef : this.viewContainer,
          dualMailer : this.promotion.ratecards[rowid].dualmailers[id],
          promoId : this.promotion.promo_id
        }
      });
    }

    private createOrUpdateBudget(ratecard,dm) {
      //Check if rateplan is already present
      let itemIndex = this.productDMBudgetList.findIndex(item => item.packageName === ratecard.pname);
      if(itemIndex === -1){
        //Not present,create new
        let newBudget = new CalculatedBudget();
        newBudget.packageName = ratecard.pname
        newBudget.totalSelected = dm.value
        let spent:number = dm.value * ratecard.prate
        newBudget.totalSpent = spent
        this.productDMBudgetList.push(newBudget)
      } else {
        //Already present,Update
        let presentBudget = this.productDMBudgetList[itemIndex];
        //If value is 0 remove from budget list
        if(dm.value==0){
          this.productDMBudgetList.splice(itemIndex, 1);
        } else {
          presentBudget.packageName = ratecard.pname
          presentBudget.totalSelected = dm.value
          let spent = dm.value * ratecard.prate
          presentBudget.totalSpent = spent
        } 
      }
    }
  }
  //Modal Class for Budget calculation table
  class CalculatedBudget {
    packageName:string;
    totalSelected:number;
    totalSpent:number;
  }
