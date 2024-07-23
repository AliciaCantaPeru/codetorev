import { DirectivesModule } from '@shared/directives/directives.module';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AutenticacionRoutingModule } from './autenticacion-routing.module';
import { AutenticacionComponent } from './autenticacion.component';
import { MaterialModule } from '@shared/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { SvgComponentModule } from '@shared/components/svg-component/svg-component.module';

@NgModule({
  declarations: [AutenticacionComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DirectivesModule,
    AutenticacionRoutingModule,
  ],
})
export class AutenticacionModule {}
