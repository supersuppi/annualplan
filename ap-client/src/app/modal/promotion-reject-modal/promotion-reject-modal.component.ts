import { Component, OnInit, ComponentRef } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

@Component({
  selector: 'app-promotion-reject-modal',
  templateUrl: './promotion-reject-modal.component.html',
  styleUrls: ['./promotion-reject-modal.component.scss']
})
export class PromotionRejectModalComponent implements OnInit {

  private internalActionButtons = [];

  constructor() { }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;

    this.internalActionButtons.push({
      text: 'Send',
      buttonClass: 'btn btn-primary',
      onAction: () => this.sendMessage()
    });

    this.internalActionButtons.push({
      text: 'Cancel',
      buttonClass: 'btn btn-danger',
      onAction: () => true
    });
  }

  sendMessage():boolean {
    console.log("Message Sent");
    return true;
  }
}
