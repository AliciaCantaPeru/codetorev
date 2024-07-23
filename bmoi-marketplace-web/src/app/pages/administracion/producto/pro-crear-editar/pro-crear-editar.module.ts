import { AuxProService } from './pro-crear-editar.service';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { ProCrearEditarRoutingModule } from './pro-crear-editar-routing.module';
import { ProCrearEditarComponent } from './pro-crear-editar.component';
import { DirectivesModule } from '@shared/directives/directives.module';
import { PipesModule } from '@shared/pipes/pipes.module';
import { TableOptionsModule } from '@shared/components/table-options/table-options.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
import { ChangeDetectorRef, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InformacionBasicaComponent } from './informacion-basica/informacion-basica.component';
import { DescripcionComponent } from './descripcion/descripcion.component';
import { ImgVideoComponent } from './img-video/img-video.component';
import { IdentificadorComponent } from './identificador/identificador.component';
import { PreciosComponent } from './precios/precios.component';
import { VarianteComponent } from './variante/variante.component';
import { PersonalizacionComponent } from './personalizacion/personalizacion.component';
import { TreeNodeModule } from '@shared/components/tree-node/tree-node.module';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { UploadFileComponent } from './img-video/upload-file/upload-file.component';
import { ImgDisenioComponent } from './img-disenio/img-disenio.component';
import { ImgCustomComponent } from './img-disenio/img-custom/img-custom.component';
import { QuillModule } from 'ngx-quill';
import { DimensionPesoComponent } from './dimension-peso/dimension-peso.component';
import { EnvioComponent } from './envio/envio.component';
import { NzAnchorModule } from 'ng-zorro-antd/anchor';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { LogisticaComponent } from './logistica/logistica.component';
import { CArbolModule } from '@shared/components/c-arbol/c-arbol.module';
import { VisorImagenProdModule } from '@shared/components/visor-imagen-prod/visor-imagen-prod.module';
import { RecursoUrlModule } from '@shared/components/recurso-url/recurso-url.module';
@NgModule({
  declarations: [
    ProCrearEditarComponent,
    InformacionBasicaComponent,
    DescripcionComponent,
    ImgVideoComponent,
    IdentificadorComponent,
    PreciosComponent,
    VarianteComponent,
    EnvioComponent,
    PersonalizacionComponent,
    UploadFileComponent,
    ImgDisenioComponent,
    ImgCustomComponent,
    DimensionPesoComponent,
    LogisticaComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    TableOptionsModule,
    PipesModule,
    DirectivesModule,
    ProCrearEditarRoutingModule,
    TreeNodeModule,
    NzUploadModule,
    PerfectScrollbarModule,
    NzAnchorModule,
    ScrollingModule,
    QuillModule.forRoot(),
    CArbolModule,
    VisorImagenProdModule,
    RecursoUrlModule,
  ],
  providers: [AuxProService],
})
export class ProCrearEditarModule {}
