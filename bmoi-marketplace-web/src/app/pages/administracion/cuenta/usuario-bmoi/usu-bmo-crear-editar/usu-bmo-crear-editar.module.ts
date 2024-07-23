import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuBmoCrearEditarRoutingModule } from './usu-bmo-crear-editar-routing.module';
import { UsuBmoCrearEditarComponent } from './usu-bmo-crear-editar.component';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [UsuBmoCrearEditarComponent],
  imports: [
    CommonModule,
    UsuBmoCrearEditarRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class UsuBmoCrearEditarModule {}
