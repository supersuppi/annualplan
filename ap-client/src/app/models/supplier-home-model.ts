import { Manager } from "./manager-model";
import { PromoYearDetail } from "./promoyear-details-model";
import { UserHomeData } from "./user-home-model";
import { HomeComment } from "./home-comment-model";


export class SupplierHomeData extends UserHomeData {
    public supplierCode:Number;
    public supplierName:String;
    public supplierID:Number;
    public activePromoYear:String;
    public promoYearDetails:Array<PromoYearDetail>;
    public managers:Array<Manager>;
    public comments:Array<HomeComment>;
}