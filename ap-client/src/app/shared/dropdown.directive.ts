import { Directive, HostListener, HostBinding } from '@angular/core';

@Directive({
  selector: '[appDropdown]'
})
export class DropdownDirective {

  @HostBinding('class.open') isOpen = false;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;
  }

  @HostListener('mouseleave') onMouseLeave() {
    if (this.isOpen) {
      this.isOpen = !this.isOpen;
    }
  }

}
