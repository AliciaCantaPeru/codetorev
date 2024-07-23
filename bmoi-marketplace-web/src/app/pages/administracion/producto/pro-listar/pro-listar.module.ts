import { DirectivesModule } from '@shared/directives/directives.module';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProListarRoutingModule } from './pro-listar-routing.module';
import { ProListarComponent } from './pro-listar.component';
import { ProGridComponent } from './pro-grid/pro-grid.component';

@NgModule({
  declarations: [ProListarComponent, ProGridComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TableOptionsModule,
    PipesModule,
    DirectivesModule,
    ProListarRoutingModule,
  ],
})
export class ProListarModule {}
