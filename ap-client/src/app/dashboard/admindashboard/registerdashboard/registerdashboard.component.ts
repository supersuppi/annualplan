import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { emailValidator } from '../../../shared/validators/username-validator';
import { contactValidator } from '../../../shared/validators/contact-validator';
import { Roles } from "../../../models/roles-model";

function dropdownValidator (formControl : FormGroup) {
  console.log("Dropdown value is :"+formControl.value);
  return formControl.value !== null ? null : { invalidValue: true };
}

@Component({
  selector: 'app-registerdashboard',
  templateUrl: './registerdashboard.component.html',
  styleUrls: ['./registerdashboard.component.scss']
})
export class RegisterdashboardComponent implements OnInit {

  userRegisterGroup : FormGroup;
  roles: Roles[];
  
  constructor() { 
  }

  ngOnInit() {
    this.roles = [
      new Roles(1,"CATEGORY MANAGER"), 
      new Roles(2,"E-COM"), 
      new Roles(3,"MARKETING"), 
      new Roles(4,"ORDER"), 
      new Roles(5,"VENDOR")
    ];
    this.userRegisterGroup = new FormGroup({
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null, [Validators.required]),
      'email': new FormControl(null,[Validators.required, emailValidator]),
      'contact': new FormControl(null,[Validators.required, contactValidator]),
      'userRole': new FormControl(null, [dropdownValidator])
    });
  }

  registerUser() {
    console.log("Register is clicked");
  }

}
