import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  onLogout() {
    console.log("Clicked on Logout");
  }

  onShowProfile() {
    console.log("Clicked on profile");
  }

}
