import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Promotion, PromoStatus, PromoComment } from "../models";
import { Observable } from "rxjs";
import { ServerConfig } from "../config/server.config";

@Injectable()
export class ManagerPromotionService {

    private promotionSupplierForManagerURL = ServerConfig.API_ENDPOINT+"promotion/manager/";
    private promotionManagerStatusUpdateURL = ServerConfig.API_ENDPOINT+"promotion/manager/status/update";
    private promotionManagerPromoRejectURL = ServerConfig.API_ENDPOINT+"promotion//manager/comment/save";

    constructor(private httpClient: HttpClient) {}

    getSupplierPromotionsForManager(supplierID:Number,promoYear:String): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierForManagerURL+supplierID+"/"+promoYear, {
            responseType: 'json'
        });
    }

    changePromotionStatus(data:PromoStatus): Observable<PromoStatus>{
        return this.httpClient.post<PromoStatus>(this.promotionManagerStatusUpdateURL,data);
    }

    savePromotionRejectComment(data:PromoComment): Observable<any>{
        return this.httpClient.post<PromoComment>(this.promotionManagerPromoRejectURL,data);
    }
}