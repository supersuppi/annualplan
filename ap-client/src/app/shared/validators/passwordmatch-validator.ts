import { FormGroup } from "@angular/forms";

/**
 * Validate confirm password with password fields.
 * @param input 
 */
export function passwordMatchValidator(input : FormGroup){

    let password = input.controls.newPassword.value;
    let confirmPassword = input.controls.confirmPwd.value;

    return password === confirmPassword ? null : { validConfirmPassword : true };
}