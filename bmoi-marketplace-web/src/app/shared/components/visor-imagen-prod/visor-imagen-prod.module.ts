import { MaterialModule } from '@shared/material/material.module';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VisorImagenProdComponent } from './visor-imagen-prod.component';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';

@NgModule({
  declarations: [VisorImagenProdComponent],
  imports: [CommonModule, FormsModule, MaterialModule],
  providers: [
    {
      provide: MAT_DIALOG_DEFAULT_OPTIONS,
      useValue: { panelClass: 'mat-dialog-override' },
    },
  ],
  exports: [VisorImagenProdComponent],
})
export class VisorImagenProdModule {}
