import { Component, OnInit, ComponentRef, ViewContainerRef } from '@angular/core';
import { IModalDialog, IModalDialogOptions, ModalDialogService } from 'ngx-modal-dialog';
import { Subject, Observable, Subscription } from 'rxjs';
import { ModalService } from '../../shared/modal-services/ModalService';
import { ProductSKU } from '../../models/product-sku-model';
import { DualMailer, Product } from '../../models';
import { SupplierPromotionService } from '../../services/index';
import { BrandPromotionModalComponent } from '../brand-promotion-modal/brand-promotion-modal.component';
import { PromotionModalComponent } from '../promotion-modal/promotion-modal.component';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.scss']
})
export class AddPromotionComponent implements OnInit, IModalDialog {

  private numberOfTiles : Number;
  private tilesArray : Array<Number> = [];
  private internalActionButtons = [];
  private promotionProducts: Array<Product>;
  private parentRef : ViewContainerRef;
  private dualMailer : DualMailer;
  private rowId : Number;
  private dmId : Number;
  private promoId : Number;
  
  constructor(private modalService: ModalService, 
    private modalDialogService: ModalDialogService,
    private promotionService : SupplierPromotionService) { }

  ngOnInit() {
    this.createTilesArray();
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.numberOfTiles = options.data['dualMailer']['value'];
    this.promotionProducts = options.data['brandAndProducts'];
    this.dualMailer = options.data['dualMailer'];
    this.rowId = options.data['rowId'];
    this.dmId = options.data['dmId'];
    this.promoId = options.data['promoId'];
    // Modal needs a target on which it needs to be displayed,
    // since dialogInit will first close the existing modal and open a new one;
    // the target is lost so get a refernce of parent where it needs to be displayed
    this.parentRef = options.data['parentRef'];

    this.internalActionButtons.push({
      text: 'Close',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });
  }

  // Selecting the promotion.
  addPromotion(event) {

    this.promotionService.getSelectedProducts(
      this.promoId, this.dmId, this.rowId, event.target.id
    ).subscribe( (data) => {
      this.showProductSelectionModal (event, data)
    }, err => {
      console.log("Something went wrong");
    });

  }

  //Showing modal on cell click
  showProductSelectionModal (event, selectedProducts : Array<ProductSKU>) {

    this.modalDialogService.openDialog(this.parentRef ,{
      title: 'Brand Promotion',
      placeOnTop: true,
      // childComponent: BrandPromotionModalComponent,
      childComponent: PromotionModalComponent,
      settings: {
        closeButtonClass: 'close theme-icon-close',
      }
    });

    this.promotionService.setPromoObject(
      this.promotionProducts, event.target.id, selectedProducts, (+this.rowId)+1,
      (+this.dmId)+1, this.promoId
    );
  }

  createTilesArray () {
    for(let i =0;i < this.numberOfTiles; i++) {
      this.tilesArray.push(i+1);
    }
  }

}
