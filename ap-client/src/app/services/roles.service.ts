import { Injectable } from "@angular/core";
import { Subject, Observable } from "rxjs";
import { Roles } from "../models/roles-model";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class RolesService{

    private rolesUrl = "http://localhost:8008/apserver/user/role/";

    constructor(private httpClient: HttpClient) {}

    getUserRoles(): Observable<Roles[]>{
        return this.httpClient.get<Roles[]>(this.rolesUrl);
    }
}