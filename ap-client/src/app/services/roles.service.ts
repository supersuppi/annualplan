import { Injectable } from "@angular/core";
import { Subject, Observable } from "rxjs";
import { Roles } from "../models/roles-model";
import { HttpClient } from "@angular/common/http";
import { ServerConfig } from "../config/server.config";

@Injectable()
export class RolesService{

    private rolesUrl = ServerConfig.API_ENDPOINT+"user/role/";

    constructor(private httpClient: HttpClient) {}

    /**
     * Get all the available roles to be allocated to the user.
     */
    getUserRoles(): Observable<Roles[]>{
        return this.httpClient.get<Roles[]>(this.rolesUrl, {
            responseType: 'json'
        });
    }
}