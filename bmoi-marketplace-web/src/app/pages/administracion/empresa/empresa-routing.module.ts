import { ROL_ADMIN, ALL_ROL } from './../../../core/soporte/rol';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutModulesGuard } from '@core/guards/aut-modules.guard';
import { ROL_MASTER, ROL_REPORTES, ROL } from '@core/soporte/rol';

const routes: Routes = [
  {
    path: 'seller',
    data: {
      roles: [ROL_MASTER],
    },
    // canActivate: [AutModulesGuard],
    loadChildren: () =>
      import('./cliente/cliente.module').then((m) => m.ClienteModule),
  },
  {
    path: 'perfil',
    data: {
      roles: [ROL_ADMIN, ROL_REPORTES],
    },
    // canActivate: [AutModulesGuard],
    loadChildren: () =>
      import('./perfil/perfil.module').then((m) => m.PerfilModule),
  },
  {
    path: 'colaborador/seller',
    // canActivate: [AutModulesGuard],
    data: {
      roles: [ALL_ROL],
    },
    loadChildren: () =>
      import('./colaborador-seller/colaborador-seller.module').then(
        (m) => m.ColaboradorSellerModule
      ),
  },
  {
    path: 'colaborador/bmoi',
    loadChildren: () =>
      import('./colaborador-bmoi/colaborador-bmoi.module').then(
        (m) => m.ColaboradorBmoiModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmpresaRoutingModule {}
