import { Component, OnInit, ComponentRef, ViewEncapsulation, ViewChild } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';
import { BrandPromotionModalComponent } from '../brand-promotion-modal/brand-promotion-modal.component';
import { SkuPromotionModalComponent } from '../sku-promotion-modal/sku-promotion-modal.component';

@Component({
  selector: 'app-promotion-modal',
  templateUrl: './promotion-modal.component.html',
  styleUrls: ['./promotion-modal.component.scss']
})
export class PromotionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  public promotionType: string = 'BRAND';
  @ViewChild(BrandPromotionModalComponent)
  private brandPromoComp : BrandPromotionModalComponent;
  @ViewChild(SkuPromotionModalComponent)
  private skuPromoComp : SkuPromotionModalComponent;
  private optionEnabled : string = "";
  private promoNameModel : string;
  private promoNameInputDisabled : boolean = false;

  constructor() { }

  ngOnInit() {
    if (typeof this.promotionType === 'undefined'
      || this.promotionType === null) {
      this.promotionType = 'BRAND';
    } else {
      this.optionEnabled = this.promotionType;
      this.promoNameInputDisabled = true;
    }
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.promotionType = options.data["promoType"]; 
    this.promoNameModel = options.data["promoName"];
    // Action buttons for modal
    this.internalActionButtons.push({
      text: 'Save Promotion',
      buttonClass: 'btn btn-primary',
      onAction: () => this.savePromotion()
    });

    this.internalActionButtons.push({
      text: 'Cancel',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });

  }

  savePromotion () {

    if (this.promotionType === 'SINGLE' ) {
      this.skuPromoComp.saveSKUPromotion(this.promoNameModel);
    } else {
      this.brandPromoComp.savePromotion(this.promoNameModel);
    }

    return true;
  }

}
