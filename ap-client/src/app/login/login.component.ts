import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControlName, FormControl, Validators } from '@angular/forms';
import { emailValidator } from '../shared/validators/username-validator';
import { passwordValidator } from '../shared/validators/password-validator';
import { UserService } from '../services/user.service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLoginGroup :  FormGroup;
  invalidCreds: boolean;

  constructor(private userService : UserService, private router: Router) { }

  ngOnInit() {
    localStorage.clear();

    this.userLoginGroup = new FormGroup ({
      'email' : new FormControl(null, [Validators.required, emailValidator]),
      'password' : new FormControl(null, [Validators.required, passwordValidator])
    });
  }

  onLogin() {
    console.log("Login clicked");
    this.userService.loginUser(this.userLoginGroup.value).subscribe(
      (response: HttpResponse<any>) => {
        localStorage.setItem('validUser', 'true');
        localStorage.setItem('token', response.headers.get('Authorization'));
        this.router.navigate(['/admin/register']);
      }, error => {
        this.invalidCreds = true;
      }
    );
  }

}
