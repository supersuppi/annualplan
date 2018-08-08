import { Component, OnInit, Input, ComponentRef, ViewEncapsulation } from '@angular/core';
import { Product } from '../../models';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

@Component({
  selector: 'app-product-selection-modal',
  templateUrl: './product-selection-modal.component.html',
  styleUrls: ['./product-selection-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ProductSelectionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private brandAndProducts : Map<string, Array<Product>>;
  private productArray : Array<Product>;
  private brandKeys : Array<string>;
  private selectedProducts : Array<Product> = [];

  constructor() { 
  }

  ngOnInit() {
    // Get all the keys from map
    this.brandKeys = Array.from(this.brandAndProducts.keys());
    // Initial value of products to be displayed in modal
    this.productArray = this.brandAndProducts.get(this.brandKeys[0]);
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.brandAndProducts = new Map<string, Array<Product>>();
    // Products come as a list of objects with key value pair,
    // to map the data to a Map using this method.
    Object.keys(options.data['brandAndProducts']).forEach(key => {
      this.addParametersToMap(key, options.data['brandAndProducts'][key]);
    });

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

  savePromotion() {
    console.log("Save promotion is clicked");
    return true;
  }

  // Adding the object key and values to a map.
  addParametersToMap (key : string, values : Array<Product>) {
    this.brandAndProducts.set(key, values);
  }

  //dropdown value event change
  onValueChange(event) {
    this.productArray = this.brandAndProducts.get(event.target.value);
    console.log(this.productArray);
  }

  // Creates an array of selected products 
  onProductSelection(event, productSelection) {
    if( event.target.checked ) {
      this.selectedProducts.push(productSelection);
    } else{
      let index = this.selectedProducts.indexOf(productSelection);
      this.selectedProducts.splice(index, 1);
    }
  }

}
