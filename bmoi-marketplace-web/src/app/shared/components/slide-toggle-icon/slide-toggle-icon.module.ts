import { FormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlideToggleIconComponent } from './slide-toggle-icon.component';

@NgModule({
  declarations: [SlideToggleIconComponent],
  imports: [CommonModule, FormsModule, MaterialModule],
  exports: [SlideToggleIconComponent],
})
export class SlideToggleIconModule {}
