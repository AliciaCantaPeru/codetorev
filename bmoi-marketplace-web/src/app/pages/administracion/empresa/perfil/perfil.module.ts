import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PerfilRoutingModule } from './perfil-routing.module';
import { PerfilComponent } from './perfil.component';
import { CrearEditarUsuarioModule } from '@shared/components/crear-editar-usuario/crear-editar-usuario.module';
import { FormDireccionModule } from '@shared/components/form-direccion/form-direccion.module';
import { FormRedSocialModule } from '@shared/components/form-red-social/form-red-social.module';
import { FormDatoEmpresaModule } from '@shared/components/form-dato-empresa/form-dato-empresa.module';
import { FormContactoModule } from '@shared/components/form-contacto/form-contacto.module';
import { MaterialModule } from '@shared/material/material.module';
import { ImgViewUploadModule } from '@shared/components/img-view-upload/img-view-upload.module';

@NgModule({
  declarations: [PerfilComponent],
  imports: [
    CommonModule,
    PerfilRoutingModule,
    CrearEditarUsuarioModule,
    MaterialModule,
    FormContactoModule,
    FormDatoEmpresaModule,
    FormRedSocialModule,
    FormDireccionModule,
    ImgViewUploadModule,
  ],
})
export class PerfilModule {}
