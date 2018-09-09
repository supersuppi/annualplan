import { RateCard } from "./ratecard";
import { DualMailer } from "./dualmalier";
import { Supplier } from "../models/supplier.model";

export class Promotion {
    name: string;
    pid:Number;
    pstatus:string;
    userName:string;
    ratecards:Array<RateCard>;
    dualmailers:Array<DualMailer>;
    suppliers:Array<Supplier>;
}