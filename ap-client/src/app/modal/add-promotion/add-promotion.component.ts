import { Component, OnInit, ComponentRef, ViewEncapsulation } from '@angular/core';
import { IModalDialog, IModalDialogOptions, ModalDialogService } from 'ngx-modal-dialog';
import { Subject, Observable } from 'rxjs';
import { ModalService } from '../../shared/modal-services/ModalService';
import { ProductSelectionModalComponent } from '../product-selection-modal/product-selection-modal.component';
import { ProductSKU } from '../../models/product-sku-model';
import { DualMailer } from '../../models';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AddPromotionComponent implements OnInit, IModalDialog {

  private numberOfTiles : Number;
  private tilesArray : Array<Number> = [];
  private internalActionButtons = [];
  private promotionProducts: any;
  private parentRef : any;
  private dualMailer : DualMailer;
  
  constructor(private modalService: ModalService, 
    private modalDialogService: ModalDialogService) { }

  ngOnInit() {
    this.createTilesArray();
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.numberOfTiles = options.data['values'];
    this.promotionProducts = options.data['brandAndProducts'];
    this.dualMailer = options.data['dualMailer'];
    // Modal needs a target on which it needs to be displayed,
    // since dialogInit will first close the existing modal and open a new one;
    // the target is lost so get a refernce of parent where it needs to be displayed
    this.parentRef = options.data['parentRef'];

    this.internalActionButtons.push({
      text: 'Cancel',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });
  }

  // Selecting the promotion.
  addPromotion(event) {

    //Showing modal on cell click
    this.modalDialogService.openDialog(this.parentRef ,{
      title: 'Choose Promotion Type',
      placeOnTop: true,
      childComponent: ProductSelectionModalComponent,
      settings: {
        closeButtonClass: 'close theme-icon-close',
      },
      data: {
        brandAndProducts : this.promotionProducts,
        promo_count : event.target.id,
        selectedProducts : this.getSelectedProdBasedOnPromoCount(event.target.id)
      }
    });
  }

  getSelectedProdBasedOnPromoCount (num : Number) : Array<ProductSKU>{

    for(let i=0; i < this.dualMailer["promosku"].length; i++) {
      if( this.dualMailer.promosku[i].promo_count === num ) {
        return this.dualMailer.promosku[i].products_selected;
      }
    }

  }

  createTilesArray () {
    for(let i =0;i < this.numberOfTiles; i++) {
      this.tilesArray.push(i+1);
    }
  }

}
