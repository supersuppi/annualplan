import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { User } from "../models/user-model";
import { Observable, Subject } from "rxjs";
import { UserContact } from "../models/user-contact-model";
import { map } from "rxjs/operators";

// interface to create an object of required attributes.
interface UserInfoHeader {
    loggedIn? : boolean;
    firstName? : String;
    lastName? : String;
    phone? : Number;
}

@Injectable()
export class UserService {

    userLoggedIn = new Subject<UserInfoHeader>();
    showNameInHeader = new Subject<any>();
    public userInfo : UserInfoHeader = {}; 
    userUrl = "http://localhost:8008/apserver/user";

    constructor(private httpClient : HttpClient) {}

    setLoggedInUser() {
        
        if (localStorage.getItem('validUser')) {
            this.userInfo.loggedIn=true;
        } else {
            this.userInfo.loggedIn=false;
        }
        this.userLoggedIn.next(this.userInfo);
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

    getUser(emailAddress: string): Observable<UserContact> {
        return this.httpClient.get<UserContact>(this.userUrl+"/"+emailAddress);
    }

}