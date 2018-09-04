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

  constructor(private adminService:AdminService) { }

  ngOnInit() {
  }

  onSubmit(f:NgForm){
    let promo = new Promotion()
    promo.name = f.value.promoName;
    promo.userName = localStorage.getItem('username');
    promo.ratecards = Object.values(f.value.ratecards);
    promo.dualmailers = Object.values(f.value.dualmailers);

    this.adminService.saveAdminPromotion(promo).subscribe((response:any) => {
      console.info("saveAdminPromotion called");
    },
    error => { 
        console.error("ERROR! PromotionComponent:saveAdminPromotion = "+error);
    });
  }

  activatePromo(){
    this.adminService.activateAdminPromotion().subscribe((response:any) => {
      console.info("activatePromo called");
    },
    error => { 
        console.error("ERROR! PromotionComponent:activatePromo = "+error);
    });
  }

}
