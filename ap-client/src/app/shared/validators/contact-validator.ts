import { FormControl } from "@angular/forms";

export function contactValidator (input: FormControl) {

    let regex = /^([7-9])([0-9]{9})$/;

    return regex.test(input.value) ? null : { validNumber : false};
}