import {DualMailer, Product, RateCard} from './index'

export class Promotion {
    public promoyear: string;
    public userid: Number;
    public isEditable: boolean;
    public version: Number;
    public products: Array<Product>;
    public ratecards: Array<RateCard>;   
}