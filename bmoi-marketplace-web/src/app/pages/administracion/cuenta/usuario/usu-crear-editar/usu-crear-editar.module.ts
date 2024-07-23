import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { PipesModule } from '@shared/pipes/pipes.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuCrearEditarRoutingModule } from './usu-crear-editar-routing.module';
import { UsuCrearEditarComponent } from './usu-crear-editar.component';

@NgModule({
  declarations: [UsuCrearEditarComponent],
  imports: [
    CommonModule,
    UsuCrearEditarRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class UsuCrearEditarModule {}
