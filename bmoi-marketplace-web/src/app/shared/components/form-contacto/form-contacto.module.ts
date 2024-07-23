import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormContactoComponent } from './form-contacto.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DirectivesModule } from '@shared/directives/directives.module';

@NgModule({
  declarations: [FormContactoComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
  ],
  exports: [FormContactoComponent],
})
export class FormContactoModule {}
