import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeguridadComponent } from './seguridad.component';
import { MaterialModule } from '@shared/material/material.module';
import { SeguridadRoutingModule } from './seguridad-routing.module';

@NgModule({
  declarations: [SeguridadComponent],
  imports: [CommonModule, SeguridadRoutingModule, MaterialModule],
})
export class SeguridadModule {}
