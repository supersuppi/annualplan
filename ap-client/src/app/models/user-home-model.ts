import { Promotion } from '../form-model/admin.promotion';

export class UserHomeData {
    public userID:Number;
    public userName:String;
    public role:String;
    public activePromotions:Array<Promotion>;
}