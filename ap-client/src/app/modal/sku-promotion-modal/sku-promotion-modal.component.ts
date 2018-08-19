import { Component, OnInit, Input, ComponentRef, ViewEncapsulation } from '@angular/core';
import { IModalDialog, IModalDialogOptions,IModalDialogSettings } from 'ngx-modal-dialog';
import { Product, ProductSKU } from '../../models';
import { SupplierPromotionService } from '../../services';
import { PromotionInterface } from '../../shared/interface/PromotionInterface';

@Component({
  selector: 'app-sku-promotion-modal',
  templateUrl: './sku-promotion-modal.component.html',
  styleUrls: ['./sku-promotion-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SkuPromotionModalComponent implements OnInit {
  private internalActionButtons = [];
  private products : Array<ProductSKU>;
  private product_skus : Array<ProductSKU>;
  private savedProducts : Array<ProductSKU>;
  private deSelectedProducts : Array<ProductSKU> = [];
  private newSelectedProducts : Array<ProductSKU> = [];
  private promoCount : Number;
  private rowId : Number;
  private dmId : Number;
  private promoId : Number;
  private promoObject : PromotionInterface;

  //Pagination Start value
  p: number = 1;

  constructor(private promotionService : SupplierPromotionService) { 
  }

  ngOnInit(){
    this.promoObject = this.promotionService.getPromoObject();

    // Get only products and add in products list 
    this.products = new Array();
    for (let p of this.promoObject.brandAndProducts) {
      for (let ps of p.product_skus) {
        this.products.push(ps);
      }
    }

    this.savedProducts = 
      typeof this.promoObject.selectedProducts === "undefined" ? [] : this.promoObject.selectedProducts;
    this.promoCount = this.promoObject.promoCount;
    this.rowId = this.promoObject.rowId;
    this.dmId = this.promoObject.dmId;
    this.promoId = this.promoObject.promoId;
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<String>>) {
    options.actionButtons = this.internalActionButtons;
    this.products = options.data['products'];
    
    // Action buttons for modal
    this.internalActionButtons.push({
      text: 'Save Promotion',
      buttonClass: 'btn btn-primary',
      onAction: () => this.saveSKUPromotion()
    });

    this.internalActionButtons.push({
      text: 'Cancel',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });

  }

  saveSKUPromotion(){
    return true;
  }

  addToProductList(isChecked,product) {
    console.log(isChecked);
    console.log(product);
    if(isChecked) {
      //Add to selected product list
    } else {
      //remove selected product from list
    }
  }

}
