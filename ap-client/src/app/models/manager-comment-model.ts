export class PromoComment {
    public  promoID:String;
    public  supplierid:Number;
    public  managerid:Number;
    public  comment:String;

    constructor(promoID:String,supplierid:Number,managerid:Number){
        this.promoID = promoID;
        this.supplierid = supplierid;
        this.managerid = managerid;
    }
}