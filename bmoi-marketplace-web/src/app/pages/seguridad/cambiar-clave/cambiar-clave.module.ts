import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CambiarClaveRoutingModule } from './cambiar-clave-routing.module';
import { CambiarClaveComponent } from './cambiar-clave.component';


@NgModule({
  declarations: [CambiarClaveComponent],
  imports: [
    CommonModule,
    CambiarClaveRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
  ]
})
export class CambiarClaveModule { }
