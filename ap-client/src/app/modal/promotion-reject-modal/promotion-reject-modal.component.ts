import { Component, OnInit, ComponentRef } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

import { PromoComment } from "../../models/index";
import { ManagerPromotionService } from '../../services/index';

@Component({
  selector: 'app-promotion-reject-modal',
  templateUrl: './promotion-reject-modal.component.html',
  styleUrls: ['./promotion-reject-modal.component.scss'],
<<<<<<< HEAD
  providers: []
=======
  providers: [ManagerPromotionService]
>>>>>>> 409282b910e815442b37e73db49d4af9c0538a81
})
export class PromotionRejectModalComponent implements OnInit {

  private internalActionButtons = [];
  private promoComment : PromoComment;
   
<<<<<<< HEAD
  constructor() { }
=======
  constructor(private promotionService: ManagerPromotionService) { }
>>>>>>> 409282b910e815442b37e73db49d4af9c0538a81

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    this.internalActionButtons = options.actionButtons ;
    this.promoComment = options.data['promoCommentModel'];
  }

}
