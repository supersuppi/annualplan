import { Injectable } from "@angular/core";

@Injectable()
export class AuthService {
    /**
     * If user is authenticated successfully update data to local storage.
     */
    isUserAuthenticated(): boolean {
        if(localStorage.getItem('validUser')){
            return true;
        }
        return false;
    }
}