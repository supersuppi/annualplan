import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { UserHomeData } from '../models';
import { ServerConfig } from "../config/server.config";
import { Promotion } from "../form-model/admin.promotion";

@Injectable()
export class AdminService{
    private adminSavePromoUrl = ServerConfig.API_ENDPOINT+"admin/promotion/save";
    private adminActivatePromoUrl = ServerConfig.API_ENDPOINT+"admin/activate/promotion";
    private getAllPromotionsUrl = ServerConfig.API_ENDPOINT+"admin/promotion/all";
    private getPromotionsStatusUrl = ServerConfig.API_ENDPOINT+"admin/promotion";
    private getPromotionByIDUrl = ServerConfig.API_ENDPOINT+"admin/promotion/find";
    private updatePromoUrl = ServerConfig.API_ENDPOINT+"admin/promotion/update";

    constructor(private httpClient: HttpClient) {}

    saveAdminPromotion(data:Promotion): Observable<Object>{
        return this.httpClient.post<any>(this.adminSavePromoUrl,data);
    }

    activateAdminPromotion(pid:Number): Observable<Object>{
        return this.httpClient.get<any>(this.adminActivatePromoUrl+"/"+pid);
    }

    getAllPromotions(): Observable<Array<Promotion>>{
        return this.httpClient.get<Array<Promotion>>(this.getAllPromotionsUrl);
    }

    getPromotionsByStatus(status:String): Observable<Array<Promotion>>{
        return this.httpClient.get<Array<Promotion>>(this.getPromotionsStatusUrl+"/"+status);
    }

    getPromotionsByID(pid:Number): Observable<Promotion>{
        return this.httpClient.get<Promotion>(this.getPromotionByIDUrl+"/"+pid);
    }

    updatePromotion(data:Promotion): Observable<Object>{
        return this.httpClient.put<any>(this.updatePromoUrl,data);
    }
}