import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { passwordValidator } from '../shared/validators/password-validator';
import { contactValidator } from '../shared/validators/contact-validator';
import { passwordMatchValidator } from '../shared/validators/passwordmatch-validator';
import { UserService } from '../services/user.service';
import { JwtHelper } from '../helper/JWTHelper';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfilePasswordUpdatesValidator } from '../shared/validators/passwordsdependency-validator';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userProfileGroup : FormGroup;
  profileData : any;
  userEmailAddress : string;
  initialFormData : any;

  constructor(private userService : UserService, 
    private route: ActivatedRoute, private tokenHelper : JwtHelper,
    private router : Router) { }

  ngOnInit() {
    this.profileData = this.route.snapshot.data['profile'];
    this.createFormControls();
    this.presetFormValues();
  }

  onProfileSubmit() {
    console.log("Profile submit is clicked");
  }

  /**
   * Pre-loading the profile page with user data. 
   */
  presetFormValues() {

    this.userEmailAddress = this.tokenHelper.decodeToken(localStorage.getItem('token')).sub;
    this.userProfileGroup.controls['firstName'].setValue(this.profileData['firstName']);
    this.userProfileGroup.controls['lastName'].setValue(this.profileData['lastName']);
    this.userProfileGroup.controls['contact'].setValue(this.profileData['phone']);

    this.initialFormData = this.userProfileGroup.value;
  
  }

  createFormControls() {

    this.userProfileGroup = new FormGroup({
      'oldPassword': new FormControl(null,[ passwordValidator ]),
      'newPassword': new FormControl(null,[ passwordValidator ]),
      'confirmPwd': new FormControl(null,[ passwordValidator ]),
      'firstName':new FormControl(null,[ Validators.required ]),
      'lastName':new FormControl(null,[ Validators.required ]),
      'contact':new FormControl(null,[ Validators.required, contactValidator ])
    }, {validators: [passwordMatchValidator, ProfilePasswordUpdatesValidator]});
  
  }

  resetChanges() {
    console.log("reset is clicked");
    this.router.navigate(['\home']);
    this.userProfileGroup.reset(this.initialFormData);
  }

}
