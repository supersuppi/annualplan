import { Component, OnInit, ComponentRef } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

import { PromoComment } from "../../models/index";
import { ManagerPromotionService } from '../../services/index';

@Component({
  selector: 'app-promotion-reject-modal',
  templateUrl: './promotion-reject-modal.component.html',
  styleUrls: ['./promotion-reject-modal.component.scss'],
  providers: [ManagerPromotionService]
})
export class PromotionRejectModalComponent implements OnInit {

  private internalActionButtons = [];
  private promoComment : PromoComment;
   
  constructor(private promotionService: ManagerPromotionService) { }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    options.actionButtons = this.internalActionButtons;
    this.promoComment = options.data['promoCommentModel'];

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
    
    this.promotionService.savePromotionRejectComment(this.promoComment).subscribe((response:any) => {
      console.debug("save PromotionRejectComment Call Success");
      console.log(response);
    },
    error => { 
        console.error("ERROR! PromotionRejectModalComponent:sendMessage = "+error);
    });

    return true;
  }
}
