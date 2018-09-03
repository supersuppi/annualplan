import { RateCard } from "./ratecard";
import { DualMailer } from "./dualmalier";

export class Promotion {
    name: string; 
    ratecards:Array<RateCard>;
    dualmailers:Array<DualMailer>;
}