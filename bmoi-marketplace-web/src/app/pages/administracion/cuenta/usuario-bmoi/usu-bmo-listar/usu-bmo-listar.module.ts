import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuBmoListarRoutingModule } from './usu-bmo-listar-routing.module';
import { UsuBmoListarComponent } from './usu-bmo-listar.component';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [UsuBmoListarComponent],
  imports: [
    CommonModule,
    UsuBmoListarRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class UsuBmoListarModule {}
