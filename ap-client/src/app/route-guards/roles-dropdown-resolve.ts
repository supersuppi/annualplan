import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Roles } from "../models/roles-model";
import { RolesService } from "../services/roles.service";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

/**
 * This route guard will pre-fetch the role data from backend before loading the 
 * Admin registration component. 
 * Note: 
 * 1)Add this resolver in app.module.ts providers array. 
 * 2)Add the resolve in route path with a name .eg-roles.
 */
@Injectable()
export class RoleDropdownResolver implements Resolve<Roles> {

    constructor(private roleService: RolesService) {}

    resolve( route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<any>| Promise<any>|any {
            return this.roleService.getUserRoles();
    }
}