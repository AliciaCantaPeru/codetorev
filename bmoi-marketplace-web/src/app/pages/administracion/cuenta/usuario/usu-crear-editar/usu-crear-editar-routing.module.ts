import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuCrearEditarComponent } from './usu-crear-editar.component';

const routes: Routes = [{ path: '', component: UsuCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuCrearEditarRoutingModule { }
