import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecuperarClaveRoutingModule } from './recuperar-clave-routing.module';
import { RecuperarClaveComponent } from './recuperar-clave.component';


@NgModule({
  declarations: [RecuperarClaveComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialModule,
    RecuperarClaveRoutingModule
  ]
})
export class RecuperarClaveModule { }
