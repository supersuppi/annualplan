import {Product, RateCard} from './index'

export class Promotion {
    public promoyear: String;
    public promo_id: Number;
    public userid: Number;
    public isEditable: boolean;
    public version: Number;
    public status:String;
    public products: Array<Product>;
    public ratecards: Array<RateCard>;   
}