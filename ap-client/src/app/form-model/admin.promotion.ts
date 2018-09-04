import { RateCard } from "./ratecard";
import { DualMailer } from "./dualmalier";

export class Promotion {
    name: string;
    userName:string;
    ratecards:Array<RateCard>;
    dualmailers:Array<DualMailer>;
}