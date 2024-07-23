import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { PipesModule } from '@shared/pipes/pipes.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioListaRoutingModule } from './usuario-lista-routing.module';
import { UsuarioListaComponent } from './usuario-lista.component';

@NgModule({
  declarations: [UsuarioListaComponent],
  imports: [
    CommonModule,
    UsuarioListaRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class UsuarioListaModule {}
