import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { UserHomeData } from '../models/index';
import { ServerConfig } from "../config/server.config";

@Injectable()
export class HomeService{

    private homeDataUrl = ServerConfig.API_ENDPOINT+"home/details/";

    constructor(private httpClient: HttpClient) {}

    getUserHomePageData(userEmail:String): Observable<UserHomeData>{
        return this.httpClient.get<UserHomeData>(this.homeDataUrl+userEmail, {
            responseType: 'json'
        });
    }
}