import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../services/admin.service';
import { Promotion } from '../../../../form-model/admin.promotion';

@Component({
  selector: 'app-manage-promotion',
  templateUrl: './manage-promotion.component.html',
  styleUrls: ['./manage-promotion.component.scss']
})
export class ManagePromotionComponent implements OnInit {
  promotions:Array<Promotion>;
  constructor(private adminService:AdminService) { }

  ngOnInit() {
    this.getAllDraftPromo();
  }

  getAllDraftPromo(){
    this.adminService.getDraftPromotion().subscribe((response:Array<Promotion>) => {
      console.info("getAllDraftPromo Called");
      this.promotions = response;
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:getAllDraftPromo = "+error);
    });
  }

  activatePromotion(pid) {
    this.adminService.activateAdminPromotion(pid).subscribe((response:any) => {
      console.info("activate Promotion Called");
      this.getAllDraftPromo();
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:activate Promotion = "+JSON.stringify(error));
    });
  }

}
