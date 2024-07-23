import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ValidacionComponent } from './validacion.component';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  imports: [CommonModule, FormsModule, MatInputModule],
  declarations: [ValidacionComponent],
  exports: [ValidacionComponent],
})
export class ValidacionModule {}
