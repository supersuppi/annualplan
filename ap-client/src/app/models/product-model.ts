import {ProductSKU} from './index'

export class Product {
    public sku:Number;
    public name:String;
    public id: Number
    public brand_name:String;
    public product_skus: Array<ProductSKU>;
}