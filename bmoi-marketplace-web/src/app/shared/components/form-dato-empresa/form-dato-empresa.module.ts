import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormDatoEmpresaComponent } from './form-dato-empresa.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DirectivesModule } from '@shared/directives/directives.module';

@NgModule({
  declarations: [FormDatoEmpresaComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
  ],
  exports: [FormDatoEmpresaComponent],
})
export class FormDatoEmpresaModule {}
