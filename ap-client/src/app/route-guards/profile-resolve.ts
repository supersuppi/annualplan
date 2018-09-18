import { UserContact } from "../models/user-contact-model";
import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "../services/user.service";
import { JwtHelper } from "../helper/JWTHelper";

/**
 * Fetch the user data before displaying to the user.
 */
@Injectable()
export class ProfileDataResolver implements Resolve<UserContact> {

    userEmailAddress : string;

    constructor(private userService : UserService,
        private tokenHelper : JwtHelper) {}

    resolve(route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<any> | Promise<any>|any {
        // Fetch email address from token public claim.
        this.userEmailAddress = this.tokenHelper.decodeToken(localStorage.getItem('token')).sub;

        return this.userService.getUserProfile(this.userEmailAddress);
    }
}