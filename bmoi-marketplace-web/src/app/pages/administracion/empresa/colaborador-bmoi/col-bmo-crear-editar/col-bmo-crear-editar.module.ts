import { FormContactoModule } from './../../../../../shared/components/form-contacto/form-contacto.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ColBmoCrearEditarRoutingModule } from './col-bmo-crear-editar-routing.module';
import { ColBmoCrearEditarComponent } from './col-bmo-crear-editar.component';
import { PipesModule } from '@shared/pipes/pipes.module';
import { MaterialModule } from '@shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ImgViewUploadModule } from '@shared/components/img-view-upload/img-view-upload.module';

@NgModule({
  declarations: [ColBmoCrearEditarComponent],
  imports: [
    CommonModule,
    ColBmoCrearEditarRoutingModule,
    FormContactoModule,
    PipesModule,
    MaterialModule,
    ReactiveFormsModule,
    ImgViewUploadModule,
  ],
})
export class ColBmoCrearEditarModule {}
