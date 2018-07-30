import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient,HttpHeaders } from "@angular/common/http";

import { Promotion } from "../models/index";

@Injectable()
export class PromotionService{

    private promotionSupplierGetURL = "http://localhost:8008/apserver/promotion/supplier/";
    private promotionSupplierSaveURL = "http://localhost:8008/apserver/promotion/save/";
    private promotionSupplierForManagerURL = "http://localhost:8008/apserver/promotion/manager/";

    constructor(private httpClient: HttpClient) {}

    getSupplierPromotions(supplierID:Number,promoYear:String): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierGetURL+supplierID+"/"+promoYear, {
            responseType: 'json'
        });
    }

    saveSupplierPromotions(data:Promotion): Observable<Object>{
        return this.httpClient.post<Promotion>(this.promotionSupplierSaveURL,data, {
            headers: new HttpHeaders().set('Content-Type', 'application/json')
        });
    }

    getSupplierPromotionsForManager(supplierID:Number,promoYear:String): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierForManagerURL+supplierID+"/"+promoYear, {
            responseType: 'json'
        });
    }
}