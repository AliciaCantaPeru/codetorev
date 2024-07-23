import { ROL_ADMIN } from './../../../core/soporte/rol';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutModulesGuard } from '@core/guards/aut-modules.guard';
import { ROL_MASTER } from '@core/soporte/rol';

const routes: Routes = [
  {
    path: '',
    data: {
      roles: [ROL_MASTER, ROL_ADMIN],
    },
    loadChildren: () =>
      import('./pro-listar/pro-listar.module').then((m) => m.ProListarModule),
  },
  {
    path: 'registro/:id',
    data: {
      roles: [ROL_MASTER, ROL_ADMIN],
    },
    loadChildren: () =>
      import('./pro-crear-editar/pro-crear-editar.module').then(
        (m) => m.ProCrearEditarModule
      ),
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductoRoutingModule {}
