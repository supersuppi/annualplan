import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { User } from "../models/user-model";
import { Observable } from "rxjs";
import { UserContact } from "../models/user-contact-model";

@Injectable()
export class UserService {

    userUrl = "http://localhost:8008/apserver/user";

    constructor(private httpClient : HttpClient) {}

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

    getUserProfile(emailAddress: string)  {
        return this.httpClient.get(this.userUrl+"/profile/"+emailAddress).subscribe(
            (response: HttpResponse<UserContact>) => {
                console.log(response);
            },
            err => { 

            }
        );
    }
}