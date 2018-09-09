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
  displayName : string;

  constructor(private router : Router, 
      private userService : UserService) { }

  ngOnInit() {
    this.subscription = this.userService.userLoggedIn.subscribe(
      (data) => {
        console.log(data.loggedIn);
        this.loggedInUser = data.loggedIn;
        this.displayName = localStorage.getItem('name');
      }
    );
  }

  onShowDashboard() {
    this.router.navigate(['\home']);
  }

  onLogout() {
    localStorage.clear();
    this.userService.setLoggedInUser();
    this.router.navigate(['\login']);
  }

  onShowProfile() {
    this.router.navigate(['\profile']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
