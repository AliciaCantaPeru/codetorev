import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appNoClose]'
})
export class NoCloseDirective {
  @HostListener("click", ["$event"])
  onClick(e: MouseEvent) {
    e.stopPropagation();
    return true;
  }
}
