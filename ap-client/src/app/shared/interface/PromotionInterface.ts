import { Product, ProductSKU } from "../../models";

export interface PromotionInterface {
    
    brandAndProducts? : Array<Product>;
    promoCount? : Number;
    selectedProducts? : Array<ProductSKU>;
    rowId? : Number;
    dmId? : Number;
    promoId? : Number;
    promoName? : string;
    promoType? : string;

}