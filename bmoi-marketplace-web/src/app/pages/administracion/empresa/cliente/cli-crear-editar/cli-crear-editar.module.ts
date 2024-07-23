import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CliCrearEditarRoutingModule } from './cli-crear-editar-routing.module';
import { CliCrearEditarComponent } from './cli-crear-editar.component';
import { CrearEditarUsuarioModule } from '@shared/components/crear-editar-usuario/crear-editar-usuario.module';

@NgModule({
  declarations: [CliCrearEditarComponent],
  imports: [
    CommonModule,
    CliCrearEditarRoutingModule,
    CrearEditarUsuarioModule,
  ],
})
export class CliCrearEditarModule {}
