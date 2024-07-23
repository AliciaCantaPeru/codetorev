import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableActionsComponent } from './table-actions.component';
import { MaterialModule } from '@shared/material/material.module';

@NgModule({
  declarations: [TableActionsComponent],
  imports: [CommonModule, MaterialModule],
  exports: [TableActionsComponent],
})
export class TableActionsModule {}
