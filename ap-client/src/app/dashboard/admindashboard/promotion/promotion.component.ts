import { Component, OnInit } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';
import { Promotion } from '../../../form-model/admin.promotion';
import { AdminService } from '../../../services/admin.service';
import { ToastNotificationService } from '../../../services/toast-notification.service';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.scss']
})
export class PromotionComponent implements OnInit {
  promoName:any;

  constructor(private adminService:AdminService,private toast:ToastNotificationService) { }

  ngOnInit() {
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
  ratecardData = [];

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
  dualmailerData = [];

  savePromo(){
    let promo = new Promotion()
    promo.name = this.promoName;
    promo.userName = localStorage.getItem('username');
    promo.ratecards = this.ratecardData.slice().reverse();//Sort
    promo.dualmailers = this.dualmailerData.slice().reverse();//Sort

    console.log(promo);
    this.adminService.saveAdminPromotion(promo).subscribe((response:any) => {
      console.info("saveAdminPromotion called");
      //Reset data
      this.ratecardData = [];
      this.dualmailerData = [];
      this.promoName = '';
      this.toast.showSuccess("Promotion Saved");
    },
    error => { 
        console.error("ERROR! PromotionComponent:saveAdminPromotion = "+error);
        this.toast.showError("Something went wrong!Try again");
    });
  }
  
}
