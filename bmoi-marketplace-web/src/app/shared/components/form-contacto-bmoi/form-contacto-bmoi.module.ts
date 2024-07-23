import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormContactoBmoiComponent } from './form-contacto-bmoi.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DirectivesModule } from '@shared/directives/directives.module';

@NgModule({
  declarations: [FormContactoBmoiComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
  ],
  exports: [FormContactoBmoiComponent],
})
export class FormContactoBmoiModule {}
