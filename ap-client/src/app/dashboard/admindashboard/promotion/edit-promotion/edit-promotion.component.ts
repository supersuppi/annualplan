import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../services/admin.service';
import { Promotion } from '../../../../form-model/admin.promotion';
import { ToastNotificationService } from '../../../../services/toast-notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-promotion',
  templateUrl: './edit-promotion.component.html',
  styleUrls: ['./edit-promotion.component.scss']
})
export class EditPromotionComponent implements OnInit {
  promotionID:Number;
  promoName:string;
  ratecardData = [];
  dualmailerData = [];

  constructor(private adminService:AdminService,private toast:ToastNotificationService,private router: Router) { }

  ngOnInit() {
    this.promotionID = Number(localStorage.getItem("editPid"));
    console.log(this.promotionID);
    this.getPromoByID( this.promotionID);
  }

  ratecardSettings = {
    actions : {
      columnTitle:'Actions',
      position:'left'
    },
    columns: {
      code: {
        title: 'Code',
        sort:false,
        filter: false
      },
      name: {
        title: 'Package Name',
        sort:false,
        filter: false
      },
      rate: {
        title: 'Rate',
        sort:false,
        filter: false
      }
    }
  };
 

  dualmailerSettings = {
    actions : {
      columnTitle:'Actions',
      position:'left'
    },
    columns: {
      code: {
        title: 'Code',
        sort:false,
        filter: false
      },
      startDate: {
        title: 'Start-Date',
        sort:false,
        filter: false
      },
      endDate: {
        title: 'End-Date',
        sort:false,
        filter: false
      }
    }
  };

  getPromoByID(prmotionID:Number){
    this.adminService.getPromotionsByID(prmotionID).subscribe((response:Promotion) => {
      console.info("getPromoByID Called");
      this.promoName = response.name;
      this.ratecardData = response.ratecards;
      this.dualmailerData = response.dualmailers;
    },
    error => { 
        console.error("ERROR! EditPromotionComponent:getPromoByID = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

  updatePromo(){
    let promo = new Promotion()
    promo.name = this.promoName;
    promo.pid = this.promotionID;
    promo.userName = localStorage.getItem('username');
    promo.ratecards = this.ratecardData
    promo.dualmailers = this.dualmailerData

    console.log(promo);
    this.adminService.updatePromotion(promo).subscribe((response:String) => {
      console.info("update Promotion called");
      this.toast.showSuccess("Promotion updated");
    },
    error => { 
        console.error("ERROR! EditPromotionComponent:updatePromo = "+JSON.stringify(error));
        this.toast.showError("Something went wrong!Try again");
    });
  }

}
