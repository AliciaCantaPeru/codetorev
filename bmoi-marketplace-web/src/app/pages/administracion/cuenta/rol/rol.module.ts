import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RolRoutingModule } from './rol-routing.module';
import { RolComponent } from './rol.component';
import { RolCrearEditarComponent } from './rol-crear-editar/rol-crear-editar.component';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [RolComponent, RolCrearEditarComponent],
  imports: [
    CommonModule,
    RolRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class RolModule {}
