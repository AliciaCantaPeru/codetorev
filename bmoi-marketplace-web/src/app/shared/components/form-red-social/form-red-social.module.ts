import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormRedSocialComponent } from './form-red-social.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FormRedSocialComponent],
  imports: [CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  exports: [FormRedSocialComponent],
})
export class FormRedSocialModule {}
