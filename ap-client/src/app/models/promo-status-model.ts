export class PromoStatus {
    public supplierid:Number;
    public promoid:Number;
   // public annualpromoid:Number;
    public currentStatus:String;
    public statusChangeTo:String;
    public statusChangeSuccess:Boolean;
    public promoYear: String;

    constructor(promoid:Number,supplierid: Number,currentStatus:String,statusChangeTo:String,promoYear: String) {
        this.promoid = promoid;
        //this.annualpromoid = annualpromoid;
        this.supplierid = supplierid;
        this.currentStatus = currentStatus;
        this.statusChangeTo = statusChangeTo;
        this.promoYear = promoYear;
    }
}