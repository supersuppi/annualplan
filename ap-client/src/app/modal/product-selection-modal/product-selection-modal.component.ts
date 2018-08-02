import { Component, OnInit, Input, ComponentRef } from '@angular/core';
import { ModalService } from '../../shared/modal-services/ModalService';
import { Product } from '../../models';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

@Component({
  selector: 'app-product-selection-modal',
  templateUrl: './product-selection-modal.component.html',
  styleUrls: ['./product-selection-modal.component.scss']
})
export class ProductSelectionModalComponent implements OnInit, IModalDialog {

  private internalActionButtons = [];
  private model = { productSelect : 'single'};
  private promotionalProduct : any;

  constructor(private modalService: ModalService) { 
  }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.promotionalProduct = options.data['promotion'];

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
  }

}
