import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./usuario-lista/usuario-lista.module').then(
        (m) => m.UsuarioListaModule
      ),
  },
  {
    path: 'registro/:id',
    loadChildren: () =>
      import('./usu-crear-editar/usu-crear-editar.module').then(
        (m) => m.UsuCrearEditarModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsuarioRoutingModule {}
