import { SupplierHomeData } from './supplier-home-model';
import { UserHomeData } from './user-home-model';

export class ManagerHomeData extends UserHomeData {
    public mamangerName:String;
    public managerID:Number;
    public suppliers:Array<SupplierHomeData>;
}