import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.scss']
})
export class AdmindashboardComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  onShowDashboard() {
    console.log("Admin dashboard Clicked");
    // navigate to 'admin/register' route, for admin to register a user.
    this.router.navigate(['register'], {relativeTo: this.route});
  }
   
  onShowProfile() {
    console.log("Admin Profile clicked");
    // navigate to 'admin/register' route, for admin to view his profile.
    this.router.navigate(['profile'], {relativeTo: this.route});
  }

  newPromotion() {
    console.log("promotion tab clicked");
    // navigate to 'admin/register' route, for admin to view his profile.
    this.router.navigate(['promotion/new'], {relativeTo: this.route});
  }

  showPromotion() {
    console.log("promotion tab clicked");
    // navigate to 'admin/register' route, for admin to view his profile.
    this.router.navigate(['promotion/show'], {relativeTo: this.route});
  }

}
