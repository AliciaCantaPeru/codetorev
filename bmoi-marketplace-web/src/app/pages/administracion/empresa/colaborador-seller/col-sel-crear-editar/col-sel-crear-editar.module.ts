import { FormContactoModule } from './../../../../../shared/components/form-contacto/form-contacto.module';
import { ImgViewUploadModule } from './../../../../../shared/components/img-view-upload/img-view-upload.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './../../../../../shared/material/material.module';
import { TableOptionsModule } from './../../../../../shared/components/table-options/table-options.module';
import { PipesModule } from './../../../../../shared/pipes/pipes.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ColSelCrearEditarRoutingModule } from './col-sel-crear-editar-routing.module';
import { ColSelCrearEditarComponent } from './col-sel-crear-editar.component';

@NgModule({
  declarations: [ColSelCrearEditarComponent],
  imports: [
    CommonModule,
    ColSelCrearEditarRoutingModule,
    FormContactoModule,
    PipesModule,
    MaterialModule,
    ReactiveFormsModule,
    ImgViewUploadModule,
  ],
})
export class ColSelCrearEditarModule {}
