import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

import { UserHomeData } from '../models/index';

@Injectable()
export class HomeService{

    private homeDataUrl = "http://localhost:8008/apserver/home/details/";

    constructor(private httpClient: HttpClient) {}

    getUserHomePageData(userEmail:String): Observable<UserHomeData>{
        return this.httpClient.get<UserHomeData>(this.homeDataUrl+userEmail, {
            responseType: 'json'
        });
    }
}