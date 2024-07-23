import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./col-bmo-listar/col-bmo-listar.module').then(
        (m) => m.ColBmoListarModule
      ),
  },
  {
    path: 'registro/:id',
    loadChildren: () =>
      import('./col-bmo-crear-editar/col-bmo-crear-editar.module').then(
        (m) => m.ColBmoCrearEditarModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ColaboradorBmoiRoutingModule {}
