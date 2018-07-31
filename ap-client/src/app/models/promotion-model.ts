import {DualMailer, Product, RateCard} from './index'

export class Promotion {
    public promoyear: String;
    public userid: Number;
    public isEditable: boolean;
    public version: Number;
    public status:String;
    public products: Array<Product>;
    public ratecards: Array<RateCard>;   
}