import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { emailValidator } from '../../../shared/validators/username-validator';
import { contactValidator } from '../../../shared/validators/contact-validator';

@Component({
  selector: 'app-registerdashboard',
  templateUrl: './registerdashboard.component.html',
  styleUrls: ['./registerdashboard.component.scss']
})
export class RegisterdashboardComponent implements OnInit {

  userRegisterGroup : FormGroup;

  constructor() { }

  ngOnInit() {
    this.userRegisterGroup = new FormGroup({
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null, [Validators.required]),
      'email': new FormControl(null,[Validators.required, emailValidator]),
      'contact': new FormControl(null,[Validators.required, contactValidator])
    });
  }

  registerUser() {
    console.log("Register is clicked");
  }

}
