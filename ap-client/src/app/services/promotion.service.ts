import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient,HttpHeaders } from "@angular/common/http";

import { Promotion } from "../models/index";

@Injectable()
export class PromotionService{

    private promotionSupplierGetUrl = "http://localhost:8008/apserver/promotion/supplier/";
    private promotionSupplierSaveUrl = "http://localhost:8008/apserver/promotion/save/";

    constructor(private httpClient: HttpClient) {}

    getSupplierPromotions(supplierID:Number,promoYear:String): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierGetUrl+supplierID+"/"+promoYear, {
            responseType: 'json'
        });
    }

    saveSupplierPromotions(data:Promotion): Observable<Object>{
        return this.httpClient.post<Promotion>(this.promotionSupplierSaveUrl,data, {
            headers: new HttpHeaders().set('Content-Type', 'application/json')
        });
    }
}