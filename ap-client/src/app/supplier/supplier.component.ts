import { Component, OnInit, ViewContainerRef} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Promotion, PromoStatus, ProductSKU, RateCard, DualMailer } from "../models/index";
import { ModalDialogService } from 'ngx-modal-dialog';
import { AddPromotionComponent } from '../modal/add-promotion/add-promotion.component';
import { SupplierPromotionService } from '../services/index';
import { SkuPromotionModalComponent } from '../modal/sku-promotion-modal/sku-promotion-modal.component';
import { map } from '../../../node_modules/rxjs/operators';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {

   private promotion:Promotion;
   private promoStatus:PromoStatus;
   public pageLoaded:Boolean; //to avoid promotion undefined error
   public hasError:Boolean;
   private activePromoYear:String;
   private products:Array<ProductSKU>;
   private productDMBudgetList:Array<CalculatedBudget>;
   private promoSaved:Boolean;

  constructor(private promotionService: SupplierPromotionService,private _Activatedroute:ActivatedRoute,
    private modalDialogService: ModalDialogService, private viewContainer: ViewContainerRef) {}

  ngOnInit() {
    this.pageLoaded = false;
    this.hasError = false;
    this.promoSaved = false;
    this.activePromoYear = this._Activatedroute.snapshot.params['pyear'];
    let sid:Number = Number(localStorage.getItem('supplierID'))
    this.getSupplierPromotion(sid,this.activePromoYear);
  }

  getSupplierPromotion(id:Number,promoyear:String) {
    this.promotionService.getSupplierPromotions(id,promoyear).subscribe((sPromotion:Promotion) => {
      console.debug("Get SupplierPromotion Call Success");
      this.promotion = sPromotion;
      this.pageLoaded =true;
      console.debug(sPromotion);
      //Create budget table data
      this.initBudgetTableData(sPromotion.ratecards);
    },
    error => { 
      this.hasError = true;
      console.error("ERROR! SupplierComponent:getSupplierPromotion = "+error);
    });
  }

  initBudgetTableData(ratecards:Array<RateCard>) {
    console.debug(">>initBudgetTableData");
    this.productDMBudgetList = new Array();
    for (let ratecard of ratecards) {
     let dualMailers:Array<DualMailer> = ratecard.dualmailers;
     for (let dm of dualMailers) {
       if(dm.value!=0) {
        this.createOrUpdateBudget(ratecard,dm)
       }
      }
   }
   console.debug("<<initBudgetTableData");
  }

  saveSupplierPromotion() {
    this.promotionService.saveSupplierPromotions(this.promotion).subscribe((response:String) => {
      console.debug("POST saveSupplierPromotion Call Success");
      console.log(response);
      this.promoSaved = true;
      this.refreshData();
    },
    error => { 
      this.hasError = true;
      console.error("ERROR! saveSupplierPromotion:getSupplierPromotion = "+error);
    });
  }

  submitSupplierPromotion() {
    console.debug("promo year"+this.promotion.promoyear);
    this.promoStatus = new PromoStatus(this.promotion.userid,this.promotion.status,"SUBMITTED",this.promotion.promoyear)
    this.promotionService.submitSupplierPromotion(this.promoStatus).subscribe((response:PromoStatus) => {
      console.debug("POST submitSupplierPromotion Call Success");
      console.log(response);
      this.refreshData();
    },
    error => { 
      this.hasError = true;
        console.error("ERROR! submitSupplierPromotion = "+error);
    });
  }

   editDM(rowid,id) {
    console.log(rowid+"=="+id);
    console.log(this.promotion.ratecards[rowid].dualmailers[id]);

    // Showing modal on cell click
    this.modalDialogService.openDialog(this.viewContainer ,{
      title: 'SKU Level Promotion',
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

  refreshData() {
    this.getSupplierPromotion(Number(localStorage.getItem('supplierID')),this.activePromoYear);
  }

  //Event called on every table cell value change
  onValueChange(row,dm){
    if(dm.value!=0) {
      this.createOrUpdateBudget(row,dm)
    }
  }

  private createOrUpdateBudget(ratecard,dm) {
    //Check is rateplan is already present
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
