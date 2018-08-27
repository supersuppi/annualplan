import { Component, OnInit, ViewChild, ElementRef, ViewEncapsulation, Input } from '@angular/core';
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
  public pageLoaded:Boolean; //to avoid promotion undefined error
  timelineYear: number;
  selectedYear:Number;

  //Year Slider Config
  someKeyboardConfig: any = {
    start: [2018],
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

  gantt_ChartData={
    "date":"2018-01-01",
    "taskArray":[
       {
          "task":"DM1",
          "startTime":"8:30am",
          "endTime":"11:00am"
       },
       {
          "task":"DM2",
          "startTime":"9:00am",
          "endTime":"8:00pm"
       },
       {
          "task":"DM3",
          "startTime":"11:30am",
          "endTime":"1:30pm"
       },
       {
          "task":"DM4",
          "startTime":"8:30am",
          "endTime":"8:00pm"
       },
       {
          "task":"DM5",
          "startTime":"8:30am",
          "endTime":"8:00pm"
       },
       {
          "task":"DM6",
          "startTime":"6:00pm",
          "endTime":"10:00pm"
       },
       {
        "task":"DM7",
        "startTime":"10:00am",
        "endTime":"12:00pm"
       }
    ]}

    gantt_chart_options={
      rectColor:"blue", //Hex code or color name can be given
      lineColor:"black",
      labelColor:"green"
    }

  constructor(private formBuilder: FormBuilder,private homeService:HomeService,
    private router: Router) { }

  ngOnInit() {
    this.timelineYear = this.someKeyboardConfig.start;
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
        localStorage.setItem('suppliers',JSON.stringify(homeData.suppliers));
        this.pageLoaded =true;
      },
      error => { 
          console.error("ERROR! HomeComponent:getHomePageData for Manager = "+error);
      });
    }

  }

  onSliderChange(event : any) {
    console.log(this.timelineYear);
    this.selectedYear = this.timelineYear;
  }

  displaySupplierPromotion(){
    let year = this.selectedYear === undefined ? new Date().toISOString().slice(0,4) : this.selectedYear;
    console.log(year);
    if(localStorage.getItem('role') === 'ROLE_VENDOR') {
      this.router.navigate(['/supplier/',year+'-01-01']);
    } else {
      this.router.navigate(['/manager/'+this.supplierID+'/'+year+'-01-01']);
    }
  }

}
