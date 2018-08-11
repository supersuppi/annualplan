import { Component, OnInit, Input, ComponentRef, ViewEncapsulation } from '@angular/core';
import { Product } from '../../models';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';
import { ProductSKU } from '../../models/product-sku-model';
import { Subscription } from 'rxjs';
import { PromotionService } from '../../services';

@Component({
  selector: 'app-product-selection-modal',
  templateUrl: './product-selection-modal.component.html',
  styleUrls: ['./product-selection-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ProductSelectionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private product_skus : Array<ProductSKU>;
  private productArray : Array<Product>;
  private selectedProducts : Array<ProductSKU>;
  private promo_count : Number;

  constructor(private promotionService : PromotionService) { 
  }

  ngOnInit() {
    // Initial value of products to be displayed in modal
    this.product_skus = this.productArray[0].product_skus;
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.productArray = options.data['brandAndProducts'];
    this.selectedProducts = 
      typeof options.data['selectedProducts'] === "undefined" ? [] : options.data['selectedProducts'];
    this.promo_count = options.data['promo_count'];
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

  // Save the products which are selected by supplier.
  savePromotion() {
    this.promotionService.saveSelectedProducts(
      this.promo_count, this.selectedProducts
    );
    return true;
  }

  //dropdown value event change
  onValueChange(event) {
    console.log(this.productArray);
    this.productArray.forEach(product => {
      if (product.brand_name === event.target.value) {
        return this.product_skus = product.product_skus;
      }
    });
    console.log(this.product_skus);
  }

  // Creates an array of selected products 
  onProductSelection(event, productSelection) {
    if( event.target.checked ) {
      this.selectedProducts.push(productSelection);
    } else {
      let index = this.selectedProducts.indexOf(productSelection);
      this.selectedProducts.splice(index, 1);
    }
  }

  // Checkbox is "checked" if the product is already selected. 
  isSelected(product) {
    let isProductSelected : boolean = false;
    this.selectedProducts.forEach(prod => {
      if ( prod["sku"] === product["sku"] ) {
        isProductSelected = true;
      }
    });
    return isProductSelected;
  }

}
