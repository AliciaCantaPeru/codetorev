import { PipesModule } from './../../../../../shared/pipes/pipes.module';
import { TableOptionsModule } from './../../../../../shared/components/table-options/table-options.module';
import { MaterialModule } from './../../../../../shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ColSelListarRoutingModule } from './col-sel-listar-routing.module';
import { ColSelListarComponent } from './col-sel-listar.component';

@NgModule({
  declarations: [ColSelListarComponent],
  imports: [
    CommonModule,
    ColSelListarRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class ColSelListarModule {}
