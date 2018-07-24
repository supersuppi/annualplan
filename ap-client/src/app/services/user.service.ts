import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { User } from "../models/user-model";
import { Observable, Subject } from "rxjs";
import { UserContact } from "../models/user-contact-model";
import { map } from "rxjs/operators";

@Injectable()
export class UserService {

    userLoggedIn = new Subject<boolean>();
    userUrl = "http://localhost:8008/apserver/user";

    constructor(private httpClient : HttpClient) {}

    setLoggedInUser() {
        localStorage.getItem('validUser') ? 
            this.userLoggedIn.next(true) : this.userLoggedIn.next(false);
    }

    registerUser(userRegisterFormValues : Object) : Observable<any>{

        let jsonBody = JSON.stringify(userRegisterFormValues); 
        return this.httpClient.post<any>(this.userUrl+"/register", jsonBody,
        {
            observe: "body"
        });
    }

    loginUser(userLoginFormValues : Object) : Observable<any>{

        let jsonBody = JSON.stringify(userLoginFormValues); 
        return this.httpClient.post<any>(this.userUrl+"/login", jsonBody,
        {
            observe: "response"
        });
    }

    getUserProfile(emailAddress: string): Observable<UserContact> {
        return this.httpClient.get<UserContact>(this.userUrl+"/profile/"+emailAddress);
    }
}