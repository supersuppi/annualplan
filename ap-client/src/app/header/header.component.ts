import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  subscription : Subscription;
  loggedInUser : boolean;

  constructor(private router : Router, 
      private userService : UserService) { }

  ngOnInit() {
    console.log("Header component is loaded");
    this.subscription = this.userService.userLoggedIn.subscribe(
      (isUserValid) => {
        console.log(isUserValid);
        this.loggedInUser = isUserValid;
      }
    );
  }

  onShowDashboard() {
    console.log("Clicked on home");
    this.router.navigate(['\home']);
  }

  onLogout() {
    console.log("Clicked on Logout");
    localStorage.clear();
    this.userService.setLoggedInUser();
    this.router.navigate(['\login']);
  }

  onShowProfile() {
    console.log("Clicked on profile");
    this.router.navigate(['\profile']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
