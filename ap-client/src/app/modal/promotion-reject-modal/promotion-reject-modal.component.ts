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
    this.internalActionButtons = options.actionButtons ;
    this.promoComment = options.data['promoCommentModel'];
  }

}
