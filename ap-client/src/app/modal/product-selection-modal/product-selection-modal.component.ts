import { Component, OnInit } from '@angular/core';
import { ModalService } from '../../shared/modal-services/ModalService';
import { Product } from '../../models';

@Component({
  selector: 'app-product-selection-modal',
  templateUrl: './product-selection-modal.component.html',
  styleUrls: ['./product-selection-modal.component.scss']
})
export class ProductSelectionModalComponent implements OnInit {
  radioSelection: any;
  promotionalProduct : any;
  constructor(private modalService: ModalService) { }

  ngOnInit() {
    this.radioSelection = 'single';

    this.promotionalProduct = [
      {sku: 'SKU-10', name: 'Test1'},
      {sku: 'SKU-11', name: 'Test2'}
    ];
    console.log(this.radioSelection);
  }

  closeDialog() {
    console.log("Modal cancel is clicked");
    this.modalService.destroy();
  }

  onSelectionChange(event) {
    console.log(event);
  }

}
