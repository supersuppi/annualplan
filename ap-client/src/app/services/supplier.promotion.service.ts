import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { HttpClient,HttpHeaders } from "@angular/common/http";

import { Promotion, PromoStatus, PromoComment, PromotionSKU, ProductSKU } from "../models/index";

@Injectable()
export class SupplierPromotionService{

    private promotionSupplierGetURL = "http://localhost:8008/apserver/promotion/supplier/";
    private promotionSupplierSubmitURL = "http://localhost:8008/apserver/promotion/supplier/submit";
    private promotionSupplierSaveURL = "http://localhost:8008/apserver/promotion/save/";
    
    private promoSku : PromotionSKU;
    public promoSubject = new Subject<PromotionSKU>();

    constructor(private httpClient: HttpClient) {}

    getSupplierPromotions(supplierID:Number,promoYear:String): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.promotionSupplierGetURL+supplierID+"/"+promoYear, {
            responseType: 'json'
        });
    }

    saveSupplierPromotions(data:Promotion): Observable<Object>{
        return this.httpClient.post<Promotion>(this.promotionSupplierSaveURL,data);
    }

    submitSupplierPromotion(data:PromoStatus): Observable<PromoStatus>{
        return this.httpClient.post<PromoStatus>(this.promotionSupplierSubmitURL,data);
    }

    saveSelectedProducts ( count : Number, product : Array<ProductSKU> ) {
        this.promoSku = new PromotionSKU();

        this.promoSku.products_selected = product;
        this.promoSku.promo_count = count;

        this.promoSubject.next(this.promoSku);
    }

}