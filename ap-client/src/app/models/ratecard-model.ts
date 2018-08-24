import {DualMailer} from './index'

export class RateCard {
    public pname: string;
    public pcode: string;
    public prate: number;
    public max_tile: Number;
    public min_tile: Number;
    public max_product: Number;
    public nash_rc: Number;
    public dualmailers: Array<DualMailer>;
}