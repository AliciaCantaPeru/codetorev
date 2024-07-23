import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [],
  imports: [CommonModule, UsuarioRoutingModule],
})
export class UsuarioModule {}
