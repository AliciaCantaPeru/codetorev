import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appSoloAlfabeto]',
})
export class SoloAlfabetoDirective {
  @HostListener('keypress', ['$event'])
  keypress(event) {
    return (
      (event.charCode >= 65 && event.charCode <= 90) ||
      (event.charCode >= 97 && event.charCode <= 122) ||
      event.charCode === 32
    );
  }
}
