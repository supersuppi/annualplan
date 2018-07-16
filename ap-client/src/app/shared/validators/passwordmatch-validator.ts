import { FormGroup } from "@angular/forms";

export function passwordMatchValidator(input : FormGroup){

    let password = input.controls.newPassword.value;
    let confirmPassword = input.controls.confirmPwd.value;

    return password === confirmPassword ? null : { validConfirmPassword : true };
}