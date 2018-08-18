import { Component, OnInit, ComponentRef, ViewEncapsulation, Renderer2,  } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

@Component({
  selector: 'app-promotion-modal',
  templateUrl: './promotion-modal.component.html',
  styleUrls: ['./promotion-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class PromotionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private promotionType: string = 'BRAND';

  constructor(private renderer : Renderer2) { }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    // Action buttons for modal
    this.internalActionButtons.push({
      text: 'Save Promotion',
      buttonClass: 'btn btn-primary',
      onAction: () => true
    });

    this.internalActionButtons.push({
      text: 'Cancel',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });

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
