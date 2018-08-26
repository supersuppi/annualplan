import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { HttpClient,HttpHeaders, HttpParams } from "@angular/common/http";
import { Promotion, PromoStatus, PromoComment, PromotionSKU, ProductSKU, AddOrRemoveProducts, Product } from "../models/index";
import { PromotionInterface } from "../shared/interface/PromotionInterface";
import { ServerConfig } from "../config/server.config";

@Injectable()
export class SupplierPromotionService{

    private promotionSupplierGetURL = ServerConfig.API_ENDPOINT+"promotion/supplier/";
    private promotionSupplierSubmitURL = ServerConfig.API_ENDPOINT+"promotion/supplier/submit";
    private promotionSupplierSaveURL = ServerConfig.API_ENDPOINT+"promotion/save/";
    private promotionSupplierSaveOrRemoveProductsURL = ServerConfig.API_ENDPOINT+"promotion/product/save/";
    private getSelectedProductsURL = ServerConfig.API_ENDPOINT+"promotion/product/fetch/";
    
    private promoSku : PromotionSKU;
    public promoSubject = new Subject<PromotionSKU>();
    private promotionObject: PromotionInterface = {} ;

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

    saveOrRemoveSelectedProducts( data : AddOrRemoveProducts ): Observable<any> {
        return this.httpClient.post<AddOrRemoveProducts>( this.promotionSupplierSaveOrRemoveProductsURL, data );
    }

    getSelectedProducts( promoId : Number, dmId : any, rowId : any,
         promoCount: any) : Observable<Array<ProductSKU>>{

        return this.httpClient.get<Array<ProductSKU>>(this.getSelectedProductsURL+promoId, {
            params: {
                dmId : dmId+1,
                rowId : rowId+1,
                promoCount : promoCount
            }
        });
    }

    setPromoObject( productArray : Array<Product>, eventId : Number, selectedProducts : Array<ProductSKU>,
        rowId : Number, dmId : Number, promoId : Number, promoName : string) {
            this.promotionObject.brandAndProducts = productArray;
            this.promotionObject.promoCount = eventId;
            this.promotionObject.selectedProducts = selectedProducts;
            this.promotionObject.rowId = rowId;
            this.promotionObject.dmId = dmId;
            this.promotionObject.promoId = promoId;
            this.promotionObject.promoName = promoName;
    }

    getPromoObject() : PromotionInterface {
        return this.promotionObject;
    }

    getIndex (selectedProductArray : Array<ProductSKU>, product : ProductSKU ) : number {
        let index; 
        for ( let i = 0; i < selectedProductArray.length; i++ ) {
            if ( selectedProductArray[i].sku == product.sku ) {
            index = i;
            break;
            }
        }
        return index;
    }

    isSelected(product: ProductSKU, savedProducts: Array<ProductSKU>): boolean {
        let isProductSelected : boolean = false;
        savedProducts.forEach(prod => {
            if ( prod["sku"] === product["sku"] ) {
            isProductSelected = true;
            }
        });
        return isProductSelected;
    }

}