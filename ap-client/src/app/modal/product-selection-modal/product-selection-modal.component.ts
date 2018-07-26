import { Component, OnInit } from '@angular/core';
import { ModalService } from '../../shared/modal-services/ModalService';

@Component({
  selector: 'app-product-selection-modal',
  templateUrl: './product-selection-modal.component.html',
  styleUrls: ['./product-selection-modal.component.scss']
})
export class ProductSelectionModalComponent implements OnInit {

  constructor(private modalService: ModalService) { }

  ngOnInit() {
  }

  closeDialog() {
    console.log("Modal cancel is clicked");
    this.modalService.destroy();
  }

}
