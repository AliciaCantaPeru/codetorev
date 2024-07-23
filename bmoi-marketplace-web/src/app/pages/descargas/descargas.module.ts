import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DescargasRoutingModule } from './descargas-routing.module';
import { DescargasComponent } from './descargas.component';
import { MaterialModule } from '@shared/material/material.module';
import { DirectivesModule } from '@shared/directives/directives.module';
import { ToolbarIndexModule } from '@shared/components/toolbar-index/toolbar-index.module';

@NgModule({
  declarations: [DescargasComponent],
  imports: [
    CommonModule,
    DescargasRoutingModule,
    MaterialModule,
    DirectivesModule,
    ToolbarIndexModule,
  ],
})
export class DescargasModule {}
