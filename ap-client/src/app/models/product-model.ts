import { ProductSKU } from "./product-sku-model";

export class Product {
    public sku:Number;
    public name:String;
    public id: Number
    public brand_name:String;
    public product_skus: Array<ProductSKU>;
}