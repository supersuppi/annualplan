import { Roles } from "./roles-model";

export class User {
    public email: string;
    public role: Roles;
    public password: string;
    public active: number;

    constructor( email:string, role: Roles, password:string,
        active:number) {
            this.email = email;
            this.password = password;
            this.active = active;
            this.role = role;
    }

}