import { Component, OnInit, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';
import {FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import {HomeService} from '../services/index'
import { UserHomeData } from '../models/index';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers:[HomeService],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {
  private homeContent:UserHomeData;
  private pageLoaded:Boolean; //to avoid promotion undefined error
  sample: number;
  rows : any[];
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
    this.rows = [
      {sender: "Sam", statusMessage: "Plan is accepted", date: new Date()},
      {sender: "Mark", statusMessage: "Plan is Ok", date: new Date()},
      {sender: "Ram", statusMessage: "Plan is accepted", date: new Date()},
    ];

    this.pageLoaded =false;
    this.getHomePageData(localStorage.getItem('username'));
  }

  
  getHomePageData(email:String) {
    console.debug("getHomePageData");
    this.homeService.getUserHomePageData(email).subscribe((homeData:UserHomeData) => {
      console.debug("Get getHomePageData Call Success");
      console.debug(homeData);
      this.homeContent = homeData;
      // share data
      localStorage.setItem('supplierID',this.homeContent.supplierID.toString());
      this.pageLoaded =true;
    },
    error => { 
        console.error("ERROR! HomeComponent:getHomePageData = "+error);
    });
  }

  onSliderChange(event : any) {
    console.log(this.sample);
    this.selectedYear = this.sample;
  }

  displaySupplierPromotion(){
    this.router.navigate(['/supplier/',this.selectedYear+'-01-01']);
  }

}
