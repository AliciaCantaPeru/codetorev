import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearEditarUsuarioComponent } from './crear-editar-usuario.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormContactoModule } from '../form-contacto/form-contacto.module';
import { FormDatoEmpresaModule } from '../form-dato-empresa/form-dato-empresa.module';
import { FormRedSocialModule } from '../form-red-social/form-red-social.module';
import { FormDireccionModule } from '../form-direccion/form-direccion.module';
import { ImgViewUploadModule } from '../img-view-upload/img-view-upload.module';

@NgModule({
  declarations: [CrearEditarUsuarioComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormContactoModule,
    FormDatoEmpresaModule,
    FormRedSocialModule,
    FormDireccionModule,
    ImgViewUploadModule,
  ],
  exports: [CrearEditarUsuarioComponent],
})
export class CrearEditarUsuarioModule {}
