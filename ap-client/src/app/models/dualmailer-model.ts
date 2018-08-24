import { PromotionSKU } from "./promo-sku-model";

export class DualMailer {
    public id:String;
    public value:number;
    public promosku: Array<PromotionSKU>;
}