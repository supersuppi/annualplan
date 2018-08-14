export class PromoComment {
    public  promoYear:String;
    public  supplierid:Number;
    public  managerid:Number;
    public  comment:String;

    constructor(promoYear:String,supplierid:Number,managerid:Number){
        this.promoYear = promoYear;
        this.supplierid = supplierid;
        this.managerid = managerid;
    }
}