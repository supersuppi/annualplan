import { Component, OnInit, Input, ComponentRef, ViewEncapsulation } from '@angular/core';
import { IModalDialog, IModalDialogOptions,IModalDialogSettings } from 'ngx-modal-dialog';
import { Product, ProductSKU, AddOrRemoveProducts } from '../../models';
import { SupplierPromotionService } from '../../services';
import { PromotionInterface } from '../../shared/interface/PromotionInterface';

@Component({
  selector: 'app-sku-promotion-modal',
  templateUrl: './sku-promotion-modal.component.html',
  styleUrls: ['./sku-promotion-modal.component.scss']
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

  saveSKUPromotion( promoName : string ){
    this.promotionService.saveOrRemoveSelectedProducts(this.constructDataToUpdate(promoName)).subscribe(
      () => {
        console.log("Db updated");
      }, err => {
        console.log("Something went wrong");
      });
      return true;
  }

  constructDataToUpdate (promoName : string) {
    let productsSaveOrRemove : AddOrRemoveProducts = new AddOrRemoveProducts(); 
    productsSaveOrRemove.supplierId= Number(localStorage.getItem('supplierID'));
    productsSaveOrRemove.dmId = this.dmId;
    productsSaveOrRemove.promoId = Number(localStorage.getItem('promoID'));
    productsSaveOrRemove.rcId = this.rowId;
    productsSaveOrRemove.productsSelected = this.newSelectedProducts;
    productsSaveOrRemove.productsDeselected = this.deSelectedProducts;
    productsSaveOrRemove.promoCount = this.promoCount;
    productsSaveOrRemove.promoName = promoName;
    productsSaveOrRemove.promoType = "SINGLE";

    return productsSaveOrRemove;
  }

  addToProductList(isChecked,product) {
    console.log(isChecked);
    console.log(product);
    if(isChecked) {
      this.newSelectedProducts.push(product);
    } else {
      //remove selected product from list
      let index = this.promotionService.getIndex(this.savedProducts, product);
      if (index > -1){
        this.deSelectedProducts.push(
          this.savedProducts.splice(index, 1)[0]);
      } else {
        this.newSelectedProducts.splice(
          this.newSelectedProducts.indexOf(product)
        );
      }
    }
  }

  isSelected(product) {
    return this.promotionService.isSelected(
      product, this.savedProducts
    );
  }

}
