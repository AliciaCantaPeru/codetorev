import { SafeHtmlPipe } from './safe-html.pipe';
import { SafeUrlPipe } from './safe-url.pipe';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NoCloseDirective } from './no-close.directive';
import { NumberDirective } from './number.directive';
import { DecimalDirective } from './decimal.directive';
import { SoloAlfabetoDirective } from './solo-alfabeto.directive';
import { AutocompleteDirective } from './autocomplete.directive';

@NgModule({
  declarations: [
    NoCloseDirective,
    NumberDirective,
    DecimalDirective,
    SoloAlfabetoDirective,
    SafeUrlPipe,
    SafeHtmlPipe,
    AutocompleteDirective,
  ],
  imports: [CommonModule],
  exports: [
    NoCloseDirective,
    NumberDirective,
    DecimalDirective,
    SoloAlfabetoDirective,
    SafeUrlPipe,
    AutocompleteDirective,
    SafeHtmlPipe,
  ],
})
export class DirectivesModule {}
