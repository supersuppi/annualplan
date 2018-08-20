import { Component, OnInit, Input, ComponentRef } from '@angular/core';
import { Product, AddOrRemoveProducts } from '../../models';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';
import { ProductSKU } from '../../models/product-sku-model';
import { Subscription } from 'rxjs';
import { SupplierPromotionService } from '../../services/index';
import { PromotionInterface } from '../../shared/interface/PromotionInterface';

@Component({
  selector: 'app-brand-promotion-modal',
  templateUrl: './brand-promotion-modal.component.html',
  styleUrls: ['./brand-promotion-modal.component.scss']
})
export class BrandPromotionModalComponent implements OnInit {

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
  private promoObject : PromotionInterface;

  constructor(private promotionService : SupplierPromotionService) { 
  }

  ngOnInit() {
    // Initial value of products to be displayed in modal
    this.constructObjectForTemplate();
    this.product_skus = this.productArray[0].product_skus;
  }

  constructObjectForTemplate() {
    this.promoObject = this.promotionService.getPromoObject();

    this.productArray = this.promoObject.brandAndProducts;
    this.savedProducts = 
      typeof this.promoObject.selectedProducts === "undefined" ? [] : this.promoObject.selectedProducts;
    this.promoCount = this.promoObject.promoCount;
    this.rowId = this.promoObject.rowId;
    this.dmId = this.promoObject.dmId;
    this.promoId = this.promoObject.promoId;
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
