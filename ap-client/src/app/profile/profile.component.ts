import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { passwordValidator } from '../shared/validators/password-validator';
import { contactValidator } from '../shared/validators/contact-validator';
import { passwordMatchValidator } from '../shared/validators/passwordmatch-validator';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userProfileGroup : FormGroup;

  constructor() { }

  ngOnInit() {
    this.userProfileGroup = new FormGroup({
      'oldPassword': new FormControl(null,[Validators.required, passwordValidator]),
      'newPassword': new FormControl(null,[Validators.required, passwordValidator]),
      'confirmPwd': new FormControl(null,[Validators.required, passwordValidator]),
      'firstName':new FormControl(null,[Validators.required]),
      'lastName':new FormControl(null,[Validators.required]),
      'contact':new FormControl(null,[Validators.required, contactValidator])
    }, {validators: passwordMatchValidator});
  }

}
