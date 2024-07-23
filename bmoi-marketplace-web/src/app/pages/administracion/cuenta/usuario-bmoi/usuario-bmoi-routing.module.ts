import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./usu-bmo-listar/usu-bmo-listar.module').then(
        (m) => m.UsuBmoListarModule
      ),
  },
  {
    path: 'registro/:id',
    loadChildren: () =>
      import('./usu-bmo-crear-editar/usu-bmo-crear-editar.module').then(
        (m) => m.UsuBmoCrearEditarModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsuarioBmoiRoutingModule {}
