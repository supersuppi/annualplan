export class PromoStatus {
    public supplierid:Number;
    public currentStatus:String;
    public statusChangeTo:String;
    public statusChangeSuccess:Boolean;

    constructor(supplierid: Number,currentStatus:String,statusChangeTo:String) {
        this.supplierid = supplierid;
        this.currentStatus = currentStatus;
        this.statusChangeTo = statusChangeTo;
    }
}