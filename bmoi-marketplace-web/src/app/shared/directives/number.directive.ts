import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appNumber]',
})
export class NumberDirective {
  @HostListener('keypress', ['$event'])
  keypress(event) {
    return (
      event.charCode === 0 || (event.charCode >= 48 && event.charCode <= 57)
    );
  }
}
