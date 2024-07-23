import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearEditarTipoCambioComponent } from './crear-editar-tipo-cambio.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { DirectivesModule } from '@shared/directives/directives.module';

@NgModule({
  declarations: [CrearEditarTipoCambioComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    DirectivesModule,
  ],
  exports: [CrearEditarTipoCambioComponent],
})
export class CrearEditarTipoCambioModule {}
