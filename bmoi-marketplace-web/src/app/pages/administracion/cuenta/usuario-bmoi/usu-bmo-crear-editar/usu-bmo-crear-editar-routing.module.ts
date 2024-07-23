import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuBmoCrearEditarComponent } from './usu-bmo-crear-editar.component';

const routes: Routes = [{ path: '', component: UsuBmoCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuBmoCrearEditarRoutingModule { }
