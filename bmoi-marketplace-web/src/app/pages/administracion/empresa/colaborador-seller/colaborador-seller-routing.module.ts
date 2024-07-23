import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./col-sel-listar/col-sel-listar.module').then(
        (m) => m.ColSelListarModule
      ),
    runGuardsAndResolvers: 'always',
  },
  {
    path: 'registro/:id',
    loadChildren: () =>
      import('./col-sel-crear-editar/col-sel-crear-editar.module').then(
        (m) => m.ColSelCrearEditarModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ColaboradorSellerRoutingModule {}
