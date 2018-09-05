import { Component, OnInit } from '@angular/core';
import { ControlContainer, NgForm } from '@angular/forms';
import { Promotion } from '../../../form-model/admin.promotion';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.scss']
})
export class PromotionComponent implements OnInit {
  promoName:any;

  constructor(private adminService:AdminService) { }

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
    promo.ratecards = this.ratecardData;
    promo.dualmailers = this.dualmailerData;

    console.log(promo);
    this.adminService.saveAdminPromotion(promo).subscribe((response:any) => {
      console.info("saveAdminPromotion called");
      //Reset data
      this.ratecardData = [];
      this.dualmailerData = [];
      this.promoName = '';
    },
    error => { 
        console.error("ERROR! PromotionComponent:saveAdminPromotion = "+error);
    });
  }
  
}
