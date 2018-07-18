import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { emailValidator } from '../../../shared/validators/username-validator';
import { contactValidator } from '../../../shared/validators/contact-validator';
import { Roles } from "../../../models/roles-model";
import { RolesService } from '../../../services/roles.service';
import { Observable } from 'rxjs';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute } from '@angular/router';

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
  rolesOb: Observable<Roles[]>;
  
  constructor(private rolesService : RolesService,
    private userService : UserService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    
    //Data['roles'] , the name 'roles' is from  roles : RoleDropdownResolver in routing module.
    this.rolesOb = this.activatedRoute.snapshot.data['roles'];

    this.userRegisterGroup = new FormGroup({
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null, [Validators.required]),
      'email': new FormControl(null,[Validators.required, emailValidator]),
      'phone': new FormControl(null,[Validators.required, contactValidator]),
      'roleId': new FormControl(null, [dropdownValidator])
    });

  }

  registerUser() {
    this.userService.registerUser(this.userRegisterGroup.value).subscribe();
  }

}
