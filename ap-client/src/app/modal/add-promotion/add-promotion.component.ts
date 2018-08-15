import { Component, OnInit, ComponentRef, ViewEncapsulation, OnDestroy, AfterViewInit } from '@angular/core';
import { IModalDialog, IModalDialogOptions, ModalDialogService } from 'ngx-modal-dialog';
import { Subject, Observable, Subscription } from 'rxjs';
import { ModalService } from '../../shared/modal-services/ModalService';
import { ProductSelectionModalComponent } from '../product-selection-modal/product-selection-modal.component';
import { ProductSKU } from '../../models/product-sku-model';
import { DualMailer } from '../../models';
import { SupplierPromotionService } from '../../services/index';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AddPromotionComponent implements OnInit, IModalDialog, OnDestroy, AfterViewInit {

  private numberOfTiles : Number;
  private tilesArray : Array<Number> = [];
  private internalActionButtons = [];
  private promotionProducts: any;
  private parentRef : any;
  private dualMailer : DualMailer;
  private subs : Subscription;
  
  constructor(private modalService: ModalService, 
    private modalDialogService: ModalDialogService,
    private promotionService : SupplierPromotionService) { }

  ngOnInit() {
    this.createTilesArray();
  }

  // This method is executed when the child view is displayed completely.
  ngAfterViewInit () {
    // this 'Subject' will add the selected product to dualMailer array.
    this.subs = this.promotionService.promoSubject.subscribe((data)=> {
      console.log(data);

      for (let i = 0; i < this.dualMailer.promosku.length; i++) {
        // Remove the promotion sku before adding a new one.
        if ( this.dualMailer.promosku[i].promo_count == data["promo_count"] ) {
          this.dualMailer.promosku.splice(i,1);
          break;
        }
      }

      this.dualMailer.promosku.push(data);
    });
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

    // this.promotionService.getSelectedProducts();

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
      if( this.dualMailer.promosku[i].promo_count == num ) {
        return this.dualMailer.promosku[i].products_selected;
      }
    }

  }

  createTilesArray () {
    for(let i =0;i < this.numberOfTiles; i++) {
      this.tilesArray.push(i+1);
    }
  }

  ngOnDestroy () {
    this.subs.unsubscribe();
  }

}
