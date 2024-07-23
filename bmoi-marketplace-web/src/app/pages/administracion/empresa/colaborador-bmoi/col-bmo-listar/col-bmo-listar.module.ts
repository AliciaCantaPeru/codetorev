import { MaterialModule } from '@shared/material/material.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ColBmoListarRoutingModule } from './col-bmo-listar-routing.module';
import { ColBmoListarComponent } from './col-bmo-listar.component';
import { PipesModule } from '@shared/pipes/pipes.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [ColBmoListarComponent],
  imports: [
    CommonModule,
    ColBmoListarRoutingModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
})
export class ColBmoListarModule {}
