import { SupplierHomeData } from './supplier-home-model';
import { UserHomeData } from './user-home-model';
import { HomeComment } from './home-comment-model';

export class ManagerHomeData extends UserHomeData {
    public mamangerName:String;
    public managerID:Number;
    public suppliers:Array<SupplierHomeData>;
    public comments:Array<HomeComment>;
}