import { PromotionSKU } from "./promo-sku-model";

export class DualMailer {
    public id:String;
    public value:Number;
    public promosku: Array<PromotionSKU>;
}