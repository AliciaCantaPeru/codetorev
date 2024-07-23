import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivarCuentaRoutingModule } from './activar-cuenta-routing.module';
import { ActivarCuentaComponent } from './activar-cuenta.component';

@NgModule({
  declarations: [ActivarCuentaComponent],
  imports: [
    CommonModule,
    ActivarCuentaRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
  ],
})
export class ActivarCuentaModule {}
