import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControlName, FormControl, Validators } from '@angular/forms';
import { emailValidator } from '../shared/validators/username-validator';
import { passwordValidator } from '../shared/validators/password-validator';
import { UserService } from '../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelper } from "../helper/JWTHelper";
import { UserContact } from '../models/user-contact-model';

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
    // Post call to authenticate the user.
    this.userService.loginUser(this.userLoginGroup.value).subscribe(
      (response: HttpResponse<any>) => {

        // Get call to get the user details to show in header of the application.
        this.userService.getUser(this.userLoginGroup.value['email']).subscribe(
          (data) => {
            console.log(data);
            this.storeUserInLocalStorage(response, data);
            console.log("Login Success");
            if (localStorage.getItem('role') === 'ROLE_ADMIN') {
              this.router.navigate(['/admin/promotion/show']);
            } else {
              this.router.navigate(['/home']);
            }
        }, err => {
          console.log("Something went wrong");
          this.invalidCreds = true;
        }); 

      }, error => {
        console.log("Login Failed");
        this.invalidCreds = true;
      }
    );
  }

  storeUserInLocalStorage( response: HttpResponse<any>, data : UserContact ) {
    console.log("store User deatils In LocalStorage");
    localStorage.setItem('validUser', 'true');
    localStorage.setItem('token', response.headers.get('Authorization'));
    localStorage.setItem('username', this.tokenHelper.decodeToken(localStorage.getItem('token')).sub);
    localStorage.setItem('role', this.tokenHelper.decodeToken(localStorage.getItem('token')).auth[0].authority);
    localStorage.setItem('name', data.lastName+","+data.firstName);
  }

}
