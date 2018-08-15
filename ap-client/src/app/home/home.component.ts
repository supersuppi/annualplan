import { Component, OnInit, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';
import {FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import {HomeService} from '../services/index'
import { UserHomeData, SupplierHomeData, ManagerHomeData, HomeComment } from '../models/index';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers:[HomeService],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {
  private homeContent:UserHomeData;
  private comments:Array<HomeComment>;
  private supplierID:Number;

  private pageLoaded:Boolean; //to avoid promotion undefined error
  sample: number;
  selectedYear:Number;

  someKeyboardConfig: any = {
    start: [2017],
    step: 1,
    range: {
      min: 2017,
      max: 2020
    },
    pips: {
      mode: 'count',
      density: 3,
      values: 6,
      stepped: true
    }
  }

  constructor(private formBuilder: FormBuilder,private homeService:HomeService,
    private router: Router) { }

  ngOnInit() {
    this.sample = this.someKeyboardConfig.start;
    this.pageLoaded =false;
    this.getHomePageData(localStorage.getItem('username'));
  }

  
  getHomePageData(email:String) {
    console.debug("getHomePageData");
 
    if(localStorage.getItem('role') === 'ROLE_VENDOR') {
      this.homeService.getUserHomePageData(email).subscribe((homeData:SupplierHomeData) => {
        console.debug("Get getHomePageData for supplier Call Success");
        console.debug(homeData);
        this.homeContent = homeData;
        this.comments = (homeData.comments == null ? new Array() : homeData.comments);
        localStorage.setItem('supplierID', homeData.supplierID.toString());
        this.pageLoaded =true;
      },
      error => { 
          console.error("ERROR! HomeComponent:getHomePageData for supplier = "+error);
      });
    } else {
      this.homeService.getUserHomePageData(email).subscribe((homeData:ManagerHomeData) => {
        console.debug("Get getHomePageData for Manager Call Success");
        console.debug(homeData);
        this.homeContent = homeData;
        this.supplierID = homeData.suppliers[0].supplierID; //TODO:getting 1st sup for test
        this.comments = (homeData.comments == null ? new Array() : homeData.comments);
        localStorage.setItem('managerID', homeData.managerID.toString());
        this.pageLoaded =true;
      },
      error => { 
          console.error("ERROR! HomeComponent:getHomePageData for Manager = "+error);
      });
    }

  }

  onSliderChange(event : any) {
    console.log(this.sample);
    this.selectedYear = this.sample;
  }

  displaySupplierPromotion(){
    if(localStorage.getItem('role') === 'ROLE_VENDOR') {
      this.router.navigate(['/supplier/',this.selectedYear+'-01-01']);
    } else {
      
      this.router.navigate(['/manager/'+this.supplierID+'/'+this.selectedYear+'-01-01']);
    }
    
  }

}
