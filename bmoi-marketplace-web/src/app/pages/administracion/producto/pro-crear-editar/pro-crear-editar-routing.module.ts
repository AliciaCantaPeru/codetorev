import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProCrearEditarComponent } from './pro-crear-editar.component';

const routes: Routes = [{ path: '', component: ProCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProCrearEditarRoutingModule {}
