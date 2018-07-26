import {DualMailer} from './index'

export class Promotion {
    public pname: string;
    public pcode: string;
    public prate: Number;
    public isEditable: boolean;
    public dualmailers: Array<DualMailer>;
}