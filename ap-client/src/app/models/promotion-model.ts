import {Product, RateCard, PromotionBudget} from './index'

export class Promotion {
    public promoyear: String;
    public promo_id: Number;
    public userid: Number;
    public isEditable: boolean;
    public version: Number;
    public status:String;
    public budget:PromotionBudget;
    public products: Array<Product>;
    public ratecards: Array<RateCard>;   
}