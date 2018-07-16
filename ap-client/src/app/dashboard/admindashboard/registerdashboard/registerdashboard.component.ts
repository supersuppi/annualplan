import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { emailValidator } from '../../../shared/validators/username-validator';
import { contactValidator } from '../../../shared/validators/contact-validator';
import { Roles } from "../../../models/roles-model";
@Component({
  selector: 'app-registerdashboard',
  templateUrl: './registerdashboard.component.html',
  styleUrls: ['./registerdashboard.component.scss']
})
export class RegisterdashboardComponent implements OnInit {

  userRegisterGroup : FormGroup;
  
  constructor() { 
    let roles = [
      new Roles(1,"CATEGORY MANAGER"), 
      new Roles(2,"E-COM"), 
      new Roles(3,"MARKETING"), 
      new Roles(4,"ORDER"), 
      new Roles(5,"VENDOR")
    ];
  }

  ngOnInit() {
    this.userRegisterGroup = new FormGroup({
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null, [Validators.required]),
      'email': new FormControl(null,[Validators.required, emailValidator]),
      'contact': new FormControl(null,[Validators.required, contactValidator]),
      'userRole': new FormControl(null)
    });
  }

  registerUser() {
    console.log("Register is clicked");
  }

}
