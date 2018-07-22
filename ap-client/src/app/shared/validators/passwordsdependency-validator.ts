import { FormGroup } from "@angular/forms";

export function ProfilePasswordUpdatesValidator (formGroup : FormGroup) {

    let oldPassword = formGroup.controls.oldPassword;
    let newPassword = formGroup.controls.newPassword;

    

    if (oldPassword.dirty && oldPassword.value.length !== 0 &&
        newPassword.touched && newPassword.value === null) {
            return {
                newPasswordNeeded : true
            }
    }

    return null;

}