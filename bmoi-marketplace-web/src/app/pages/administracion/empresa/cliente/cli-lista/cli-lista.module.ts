import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CliListaRoutingModule } from './cli-lista-routing.module';
import { CliListaComponent } from './cli-lista.component';
import { MaterialModule } from '@shared/material/material.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { PipesModule } from '@shared/pipes/pipes.module';

@NgModule({
  declarations: [CliListaComponent],
  imports: [
    CommonModule,
    CliListaRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
  ],
})
export class CliListaModule {}
