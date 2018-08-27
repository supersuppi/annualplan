import { ProductSKU } from "./product-sku-model";

export class SKULevelPromoData {
    public promoName: string;
    public promoType: string;
    public productsSelected:Array<ProductSKU>;
}