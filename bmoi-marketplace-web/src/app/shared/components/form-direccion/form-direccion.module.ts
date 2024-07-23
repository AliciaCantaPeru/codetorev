import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormDireccionComponent } from './form-direccion.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FormDireccionComponent],
  imports: [CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  exports: [FormDireccionComponent],
})
export class FormDireccionModule {}
