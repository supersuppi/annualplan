import { CanActivate, ActivatedRouteSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { AuthService } from "../services/auth.service";

@Injectable()
export class RoleGuardService implements CanActivate{

    constructor(private authService: AuthService) {}

    canActivate(route: ActivatedRouteSnapshot): boolean{

        const expectedUserRole = route.data.expectedRole;

        if(this.authService.isUserAuthenticated()) {
            return true;
        } else {
            return false;
        }
    }

}