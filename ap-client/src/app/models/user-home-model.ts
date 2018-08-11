import { PromoYearDetail } from './index';

export class UserHomeData {
    public userID:Number;
    public userName:String;
    public supplierCode:Number;
    public supplierName:String;
    public supplierID:Number;
    public activePromoYear:String;
    public promoYearDetails:Array<PromoYearDetail>;
}