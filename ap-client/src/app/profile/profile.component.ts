import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { passwordValidator } from '../shared/validators/password-validator';
import { contactValidator } from '../shared/validators/contact-validator';
import { passwordMatchValidator } from '../shared/validators/passwordmatch-validator';
import { UserService } from '../services/user.service';
import { JwtHelper } from '../helper/JWTHelper';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userProfileGroup : FormGroup;
  userEmailAddress : string;

  constructor(private userService : UserService, 
    private tokenHelper : JwtHelper ) { }

  ngOnInit() {

    // Fetch email address from token public claim.
    this.userEmailAddress = this.tokenHelper.decodeToken(localStorage.getItem('token')).sub;

    this.userService.getUserProfile(this.userEmailAddress);

    this.userProfileGroup = new FormGroup({
      'oldPassword': new FormControl(null,[Validators.required, passwordValidator]),
      'newPassword': new FormControl(null,[Validators.required, passwordValidator]),
      'confirmPwd': new FormControl(null,[Validators.required, passwordValidator]),
      'firstName':new FormControl(null,[Validators.required]),
      'lastName':new FormControl(null,[Validators.required]),
      'contact':new FormControl(null,[Validators.required, contactValidator])
    }, {validators: passwordMatchValidator});
  }

  onProfileSubmit() {
    console.log("Profile submit is clicked");
  }

}
