import { DirectivesModule } from '@shared/directives/directives.module';
import { ToolbarPrincipalComponent } from './../../layout/container-page/toolbar-principal/toolbar-principal.component';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdministracionRoutingModule } from './administracion-routing.module';
import { ContainerPageComponent } from 'src/app/layout/container-page/container-page.component';
import { AppMenuComponent } from 'src/app/layout/container-page/app-menu/app-menu.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ContainerPageComponent,
    AppMenuComponent,
    ToolbarPrincipalComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    AdministracionRoutingModule,
    MaterialModule,
    DirectivesModule,
    PerfectScrollbarModule,
  ],
})
export class AdministracionModule {}
