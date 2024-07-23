import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImgViewUploadComponent } from './img-view-upload.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';

@NgModule({
  declarations: [ImgViewUploadComponent],
  imports: [CommonModule, FormsModule, MaterialModule],
  exports: [ImgViewUploadComponent],
})
export class ImgViewUploadModule {}
