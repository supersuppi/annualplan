import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { User } from "../models/user-model";
import { Observable } from "rxjs";

@Injectable()
export class UserService {

    userUrl = "http://localhost:8008/apserver/user";

    constructor(private httpClient : HttpClient) {}

    registerUser(user : Object) : Observable<any>{
        let jsonBody = JSON.stringify(user); 
        return this.httpClient.post<any>(this.userUrl+"/register", user,
        {
            observe: "body"
        });
    }

    getUserProfile () {
        this.httpClient.get(this.userUrl);
    }
}