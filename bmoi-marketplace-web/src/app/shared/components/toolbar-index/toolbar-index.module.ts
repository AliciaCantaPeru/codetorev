import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarIndexComponent } from './toolbar-index.component';
import { MaterialModule } from '@shared/material/material.module';

@NgModule({
  declarations: [ToolbarIndexComponent],
  imports: [CommonModule, MaterialModule],
  exports: [ToolbarIndexComponent],
})
export class ToolbarIndexModule {}
