import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControlName, FormControl, Validators } from '@angular/forms';
import { emailValidator } from '../shared/validators/username-validator';
import { passwordValidator } from '../shared/validators/password-validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLoginGroup :  FormGroup;


  constructor() { }

  ngOnInit() {
    this.userLoginGroup = new FormGroup ({
      'email' : new FormControl(null, [Validators.required, emailValidator]),
      'password' : new FormControl(null, [Validators.required, passwordValidator])
    });
  }

  onLogin() {
    console.log("Login clicked");
    console.log("Email is:"+this.userLoginGroup.value["email"]);
    console.log("password is:"+ this.userLoginGroup.value["password"]);
  }

}
