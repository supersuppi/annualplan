export class PromoComment {
    public  promoYear:String;
    public  supplierid:Number;
    public  comment:String;

    constructor(promoYear:String,supplierid:Number){
        this.promoYear = promoYear;
        this.supplierid = supplierid;
    }
}