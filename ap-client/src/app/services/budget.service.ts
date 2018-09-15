import { Injectable } from "@angular/core";
import { ServerConfig } from "../config/server.config";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { PromotionBudget } from "../models";

@Injectable()
export class BudgetService {
    private activePromotionUrl = ServerConfig.API_ENDPOINT+"promotion/active";
    private budgetUrl = ServerConfig.API_ENDPOINT+"promotion/budget";

    constructor(private httpClient: HttpClient) {}

    getAllActivePromotions() : Observable<any> {
        let supplierId = +(localStorage.getItem('supplierID'));
        return this.httpClient.get(this.activePromotionUrl+"/"+supplierId);
    }

    saveBudget(promotionId:number, budgetAllocated:number): Observable<any> {
        let promoBudget: PromotionBudget = new PromotionBudget();
        promoBudget.promotionId = promotionId;
        promoBudget.supplierId = +(localStorage.getItem('supplierID'));
        promoBudget.allocated = budgetAllocated;

        return this.httpClient.post(this.budgetUrl, promoBudget);
    }

    getPromotionBudget(promotionId : number) : Observable<PromotionBudget>{
        return this.httpClient.get(this.budgetUrl+"/"+promotionId);
    }

}