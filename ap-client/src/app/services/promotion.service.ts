import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

import { Promotion } from "../models/index";

@Injectable()
export class PromotionService{

    private promotionSupplierUrl = "http://localhost:8008/apserver/promotion/supplier/";

    constructor(private httpClient: HttpClient) {}

    getSupplierPromotions(supplierID:Number): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierUrl+supplierID, {
            responseType: 'json'
        });
    }
}