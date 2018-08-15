import { Component, OnInit, ComponentRef } from '@angular/core';
import { IModalDialog, IModalDialogOptions } from 'ngx-modal-dialog';

import { PromoComment } from "../../models/index";
import {PromotionService} from '../../services/index'

@Component({
  selector: 'app-promotion-reject-modal',
  templateUrl: './promotion-reject-modal.component.html',
  styleUrls: ['./promotion-reject-modal.component.scss'],
  providers: []
})
export class PromotionRejectModalComponent implements OnInit {

  private internalActionButtons = [];
  private promoComment : PromoComment;
   
  constructor() { }

  ngOnInit() {
  }

  dialogInit(reference: ComponentRef<IModalDialog>, options: Partial<IModalDialogOptions<string>>) {
    this.internalActionButtons = options.actionButtons ;
    this.promoComment = options.data['promoCommentModel'];
  }

}
