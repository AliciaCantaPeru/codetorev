import { ColaboradorSellerRoutingModule } from './colaborador-seller-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FormContactoModule } from '@shared/components/form-contacto/form-contacto.module';
import { ImgViewUploadModule } from '@shared/components/img-view-upload/img-view-upload.module';

@NgModule({
  imports: [
    CommonModule,
    ColaboradorSellerRoutingModule,
    FormContactoModule,
    PipesModule,
    TableOptionsModule,
    MaterialModule,
    ReactiveFormsModule,
    ImgViewUploadModule,
  ],
})
export class ColaboradorSellerModule {}
