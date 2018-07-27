import { Product, BrandProduct } from "./index";

export class DualMailer {
    public id:String;
    public value:Number;
    public promosku: Array<Product>;
    public promobrandsku: BrandProduct;
}