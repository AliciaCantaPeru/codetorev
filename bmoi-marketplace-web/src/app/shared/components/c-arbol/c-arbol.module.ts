import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CArbolComponent } from './c-arbol.component';
import { ItemComponent } from './item/item.component';
import { TemplateDirectiveModule } from '@shared/directives/template.directive';
import { MaterialModule } from '@shared/material/material.module';

@NgModule({
  imports: [CommonModule, TemplateDirectiveModule, MaterialModule],
  declarations: [CArbolComponent, ItemComponent],
  exports: [CArbolComponent, TemplateDirectiveModule],
})
export class CArbolModule {}
