import { Roles } from "./roles-model";

export class User {
    public firstName: string;
    public lastName: string;
    public emailAddress: string;
    public contact: number;
    public role: Roles;

    constructor(firstName: string, lastName: string, emailAddress:string,
        contact: number, role: Roles) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
            this.contact = contact;
            this.role = role;
    }


}