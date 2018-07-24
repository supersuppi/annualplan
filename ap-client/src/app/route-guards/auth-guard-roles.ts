import { CanActivate, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { AuthService } from "../services/auth.service";
import { JwtHelper } from "../helper/JWTHelper";
import { UserService } from "../services/user.service";

@Injectable()
export class RoleGuardService implements CanActivate{

    constructor(private authService: AuthService, 
        private router: Router, private jwtHelper: JwtHelper,
        private userService : UserService) {}

    canActivate(route: ActivatedRouteSnapshot): boolean{

        const expectedUserRole = route.data.expectedRole;

        const token = localStorage.getItem('token');
        const tokenPayload = this.jwtHelper.decodeToken(token);

        if(typeof expectedUserRole === 'undefined' &&
            this.authService.isUserAuthenticated()) {
            this.userService.setLoggedInUser();
            return true;
        } else if(this.authService.isUserAuthenticated()
            && tokenPayload.auth[0].authority === expectedUserRole) {
            this.userService.setLoggedInUser();
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
    }

}