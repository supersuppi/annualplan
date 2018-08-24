import { Component, OnInit, ComponentRef, ViewEncapsulation, ViewChild  } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';
import { BrandPromotionModalComponent } from '../brand-promotion-modal/brand-promotion-modal.component';
import { SkuPromotionModalComponent } from '../sku-promotion-modal/sku-promotion-modal.component';

@Component({
  selector: 'app-promotion-modal',
  templateUrl: './promotion-modal.component.html',
  styleUrls: ['./promotion-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class PromotionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private promotionType: string = 'BRAND';
  @ViewChild(BrandPromotionModalComponent)
  private brandPromoComp : BrandPromotionModalComponent;
  @ViewChild(SkuPromotionModalComponent)
  private skuPromoComp : SkuPromotionModalComponent;

  constructor() { }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
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
      this.skuPromoComp.saveSKUPromotion();
    } else {
      this.brandPromoComp.savePromotion();
    }

    return true;
  }

  showSinglePromotion(event) {
    console.log("Single promo clicked");
    this.promotionType = 'SINGLE';
  }

  showBrandPromotion(event) {
    console.log("Brand promo clicked");
    this.promotionType = 'BRAND';
  }

}
