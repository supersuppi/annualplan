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
    private getDraftPromotionsUrl = ServerConfig.API_ENDPOINT+"admin/promotion/draft";

    constructor(private httpClient: HttpClient) {}

    saveAdminPromotion(data:Promotion): Observable<Object>{
        return this.httpClient.post<any>(this.adminSavePromoUrl,data);
    }

    activateAdminPromotion(pid:Number): Observable<Object>{
        return this.httpClient.get<any>(this.adminActivatePromoUrl+"/"+pid);
    }

    getDraftPromotion(): Observable<Array<Promotion>>{
        return this.httpClient.get<Array<Promotion>>(this.getDraftPromotionsUrl);
    }
}