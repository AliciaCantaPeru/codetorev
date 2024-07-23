import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./cli-lista/cli-lista.module').then((m) => m.CliListaModule),
  },
  {
    path: 'registro/:id',
    loadChildren: () =>
      import('./cli-crear-editar/cli-crear-editar.module').then(
        (m) => m.CliCrearEditarModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClienteRoutingModule {}
