import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }

  onLogout() {
    console.log("Clicked on Logout");
    localStorage.clear();
    this.router.navigate(['\login']);
  }

  onShowProfile() {
    console.log("Clicked on profile");
  }

}
