import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TreeNodeComponent } from './tree-node.component';

@NgModule({
  declarations: [TreeNodeComponent],
  imports: [CommonModule, MaterialModule],
  exports: [TreeNodeComponent],
})
export class TreeNodeModule {}
