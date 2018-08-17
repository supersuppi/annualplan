import { Component, OnInit, Input, ComponentRef, ViewEncapsulation } from '@angular/core';
import { Product, AddOrRemoveProducts } from '../../models';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';
import { ProductSKU } from '../../models/product-sku-model';
import { Subscription } from 'rxjs';
import { SupplierPromotionService } from '../../services/index';

@Component({
  selector: 'app-brand-promotion-modal',
  templateUrl: './brand-promotion-modal.component.html',
  styleUrls: ['./brand-promotion-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class BrandPromotionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private product_skus : Array<ProductSKU>;
  private productArray : Array<Product>;
  private savedProducts : Array<ProductSKU>;
  private deSelectedProducts : Array<ProductSKU> = [];
  private newSelectedProducts : Array<ProductSKU> = [];
  private promoCount : Number;
  private rowId : Number;
  private dmId : Number;
  private promoId : Number;
  private groupName : String;

  constructor(private promotionService : SupplierPromotionService) { 
  }

  ngOnInit() {
    // Initial value of products to be displayed in modal
    this.product_skus = this.productArray[0].product_skus;
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.productArray = options.data['brandAndProducts'];
    this.savedProducts = 
      typeof options.data['selectedProducts'] === "undefined" ? [] : options.data['selectedProducts'];
    this.promoCount = options.data['promoCount'];
    this.rowId = options.data['rowId'];
    this.dmId = options.data['dmId'];
    this.promoId = options.data['promoId'];
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
    this.promotionService.saveOrRemoveSelectedProducts(this.constructDataToUpdate()).subscribe(
      () => {
        console.log("Db updated");
      }, err => {
        console.log("Something went wrong");
      });
      return true;
  }

  constructDataToUpdate () {
    let productsSaveOrRemove : AddOrRemoveProducts = new AddOrRemoveProducts(); 

    productsSaveOrRemove.dmId = this.dmId;
    productsSaveOrRemove.promoId = this.promoId;
    productsSaveOrRemove.rcId = this.rowId;
    productsSaveOrRemove.productsSelected = this.newSelectedProducts;
    productsSaveOrRemove.productsDeselected = this.deSelectedProducts;
    productsSaveOrRemove.promoCount = this.promoCount;

    return productsSaveOrRemove;
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

  // Creates an array of selected and deselected products 
  onProductSelection(event, productSelection) {
    if( event.target.checked ) {
      this.newSelectedProducts.push(productSelection);
    } else {
      // If the product is unchecked , we need to handle 2 cases.
      // 1) If the product is already selected and saved in DB and the product is deselected ,
      //  we are populating the deselected array.
      // 2) If the product is selected without saving it to promotion then we just need to splice
      // the array witout updating deselected array.
      let index = this.getIndex(this.savedProducts, productSelection);
      if ( index > -1) {
        this.deSelectedProducts.push(
          this.savedProducts.splice(index, 1)[0]);
      } else {
        this.newSelectedProducts.splice(
          this.newSelectedProducts.indexOf(productSelection,1)
        );
      }
    }
  }

  getIndex (selectedProductArray : Array<ProductSKU>, product : ProductSKU ) : number {
    let index; 
    for ( let i = 0; i < selectedProductArray.length; i++ ) {
      if ( selectedProductArray[i].sku == product.sku ) {
        index = i;
        break;
      }
    }
    return index;
  }

  // Checkbox is "checked" if the product is already selected. 
  isSelected(product) {
    let isProductSelected : boolean = false;
    this.savedProducts.forEach(prod => {
      if ( prod["sku"] === product["sku"] ) {
        isProductSelected = true;
      }
    });
    return isProductSelected;
  }

}
