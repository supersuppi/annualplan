import { FormControl } from "@angular/forms";

export function passwordValidator (input: FormControl) {

    let regex = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})/;

    // if the value doesnt match the above regex , return false.
    if(!regex.test(input.value)) {
        return {
            validPassword: false
        }
    }

    return null;
}