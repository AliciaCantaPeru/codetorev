import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appAutocomplete]',
})
export class AutocompleteDirective {
  private _chrome = navigator.userAgent.indexOf('Chrome') > -1;
  constructor(private _el: ElementRef) {}
  ngOnInit() {
    if (this._chrome) {
      if (this._el.nativeElement.getAttribute('autocomplete') === 'off') {
        setTimeout(() => {
          this._el.nativeElement.setAttribute('autocomplete', 'offoff');
        });
      }
    }
  }
}
