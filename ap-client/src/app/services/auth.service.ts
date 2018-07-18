import { Injectable } from "@angular/core";

@Injectable()
export class AuthService {
    
    isUserAuthenticated(): boolean {
        if(localStorage.getItem('validUser')){
            return true;
        }
        return false;
    }
}