import { ROL_ADMIN, ROL_CONSULTOR } from './../../../core/soporte/rol';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutModulesGuard } from '@core/guards/aut-modules.guard';
import { ROL_MASTER } from '@core/soporte/rol';

const routes: Routes = [
  {
    path: 'rol',
    data: {
      roles: [ROL_MASTER],
    },
    canActivate: [AutModulesGuard],
    loadChildren: () => import('./rol/rol.module').then((m) => m.RolModule),
  },
  {
    path: 'seller',
    data: {
      roles: [ROL_MASTER, ROL_ADMIN, ROL_CONSULTOR],
    },
    canActivate: [AutModulesGuard],
    loadChildren: () =>
      import('./usuario/usuario.module').then((m) => m.UsuarioModule),
  },
  {
    path: 'bmoi',
    data: {
      roles: [ROL_MASTER, ROL_ADMIN, ROL_CONSULTOR],
    },
    loadChildren: () =>
      import('./usuario-bmoi/usuario-bmoi.module').then(
        (m) => m.UsuarioBmoiModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CuentaRoutingModule {}
