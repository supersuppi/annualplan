import { User } from "./user-model";

export class UserContact {
    public firstName: string;
    public lastName: string;
    public phone: number;
    public user: User;

    constructor(firstName: string, lastName: string, phone:number,
        user: User) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.user = user;
    }


}