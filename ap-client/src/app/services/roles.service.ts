import { Injectable } from "@angular/core";
import { Roles } from "../models/roles-model";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class RolesService{

    private roles : Roles[];
    private rolesUrl = "http://localhost:8008/apserver/user/role/";

    constructor(private http: HttpClient) {}

    getUserRoles():Roles[] {
        this.http.get(this.rolesUrl, { headers: {
            "Access-Control-Allow-Origin" :  "*",
            "Access-Control-Allow-Credentials" : "true",
            "Content-Type" :  "application/json"
        },
            observe: "response"
        }).subscribe(data => {
            console.log(data);
        });
        return null;
    }
}