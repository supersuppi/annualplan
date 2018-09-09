import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../services/admin.service';
import { Promotion } from '../../../../form-model/admin.promotion';
import { ToastNotificationService } from '../../../../services/toast-notification.service';

@Component({
  selector: 'app-manage-promotion',
  templateUrl: './manage-promotion.component.html',
  styleUrls: ['./manage-promotion.component.scss']
})
export class ManagePromotionComponent implements OnInit {
  promotions:Array<Promotion>;
  constructor(private adminService:AdminService,private toast:ToastNotificationService) { }

  ngOnInit() {
    this.getAllDraftPromo();
  }

  getAllDraftPromo(){
    this.adminService.getDraftPromotion().subscribe((response:Array<Promotion>) => {
      console.info("getAllDraftPromo Called");
      this.promotions = response;
      if(this.promotions == null) {
        this.promotions = new Array();
      }
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:getAllDraftPromo = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

  activatePromotion(pid) {
    this.adminService.activateAdminPromotion(pid).subscribe((response:any) => {
      console.info("activate Promotion Called");
      this.getAllDraftPromo();
      this.toast.showSuccess("Promotion Activated");
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:activate Promotion = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

}
