import { ProductSKU } from ".";

export class AddOrRemoveProducts {
    public supplierId : Number;
    public promoCount : Number;
    public dmId : Number;
    public rcId : Number;
    public promoId : Number;
    public productsSelected : Array<ProductSKU>;
    public productsDeselected : Array<ProductSKU>;
    public promoName : string;
    public promoType : string;

}