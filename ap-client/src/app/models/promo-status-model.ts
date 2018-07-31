export class PromoStatus {
    public supplierid:Number;
    public currentStatus:String;
    public statusChangeTo:String;
    public statusChangeSuccess:Boolean;
    public promoYear: String;

    constructor(supplierid: Number,currentStatus:String,statusChangeTo:String,promoYear: String) {
        this.supplierid = supplierid;
        this.currentStatus = currentStatus;
        this.statusChangeTo = statusChangeTo;
        this.promoYear = promoYear;
    }
}