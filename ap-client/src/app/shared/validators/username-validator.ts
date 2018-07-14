import { FormControl } from "@angular/forms";

export function emailValidator( input: FormControl ) {

    let emailRegex  =  /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/;

    // if the value doesnt match the above regex , return false.
    if(!emailRegex.test(input.value)) {
        return {
            validEmail : false
        }
    }

    return null;
}