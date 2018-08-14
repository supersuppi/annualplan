import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControlName, FormControl, Validators } from '@angular/forms';
import { emailValidator } from '../shared/validators/username-validator';
import { passwordValidator } from '../shared/validators/password-validator';
import { UserService } from '../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelper } from "../helper/JWTHelper";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLoginGroup :  FormGroup;
  invalidCreds: boolean;

  constructor(private userService : UserService, private router: Router,private tokenHelper : JwtHelper) { }

  ngOnInit() {
    localStorage.clear();

    this.userLoginGroup = new FormGroup ({
      'email' : new FormControl(null, [Validators.required, emailValidator]),
      'password' : new FormControl(null, [Validators.required, passwordValidator])
    });
  }

  onLogin() {
    this.userService.loginUser(this.userLoginGroup.value).subscribe(
      (response: HttpResponse<any>) => {
        this.storeUserInLocalStorage(response);
        console.log("Login Success");
        this.router.navigate(['/home']);
      }, error => {
        console.log("Login Failed");
        this.invalidCreds = true;
      }
    );
  }

  storeUserInLocalStorage(response: any) {
    console.log("store User deatils In LocalStorage");
    localStorage.setItem('validUser', 'true');
    localStorage.setItem('token', response.headers.get('Authorization'));
    localStorage.setItem('username', this.tokenHelper.decodeToken(localStorage.getItem('token')).sub);
    localStorage.setItem('role', this.tokenHelper.decodeToken(localStorage.getItem('token')).auth[0].authority);
  }

}
