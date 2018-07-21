import { Roles } from "./roles-model";

export class User {

    public email: string;
    public role: Roles;
    public password: string;
    public active: number;

}