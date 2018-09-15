import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../services/admin.service';
import { Promotion } from '../../../../form-model/admin.promotion';
import { ToastNotificationService } from '../../../../services/toast-notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-promotion',
  templateUrl: './manage-promotion.component.html',
  styleUrls: ['./manage-promotion.component.scss']
})
export class ManagePromotionComponent implements OnInit {
  promotions:Array<Promotion>;
  //Pagination Start value
  p: number = 1;
  constructor(private adminService:AdminService,private toast:ToastNotificationService,private router: Router) { }

  ngOnInit() {
    this.getAllPromotions();
  }

  getAllPromotions(){
    this.adminService.getAllPromotions().subscribe((response:Array<Promotion>) => {
      console.info("getAllPromotions Called");
      this.promotions = response;
      if(this.promotions == null) {
        this.promotions = new Array();
      }
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:getAllPromotions = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

  getPromoByStatus(status:String){
    this.adminService.getPromotionsByStatus(status).subscribe((response:Array<Promotion>) => {
      console.info("getPromoByStatus Called");
      this.promotions = response;
      if(this.promotions == null) {
        this.promotions = new Array();
      }
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:getPromoByStatus = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

  activatePromotion(pid) {
    this.adminService.activateAdminPromotion(pid).subscribe((response:any) => {
      console.info("activate Promotion Called");
      this.getAllPromotions();
      this.toast.showSuccess("Promotion Activated");
    },
    error => { 
        console.error("ERROR! ManagePromotionComponent:activate Promotion = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

  onSelect(id){
    console.info("ID-"+id);
    if(id==1) {
      console.info("get PromoBy Status DRAFT");
      this.getPromoByStatus('DRAFT');
    } else if(id==2) {
      console.info("get PromoBy Status ACTIVE");
      this.getPromoByStatus('ACTIVE');
    } else if(id==3) {
      console.info("get PromoBy Status COMPLETED");
      this.getPromoByStatus('COMPLETED');
    } else {
      this.getAllPromotions();
    }
  }

  editPromotion(pid) {
    console.info("PID-"+pid);
    localStorage.setItem("editPid",pid);
    this.router.navigate(['/admin/promotion/edit']);
  }

}
