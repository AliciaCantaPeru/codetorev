import { ROL } from '@core/soporte/rol';
import { ROL_ADMIN } from './../../core/soporte/rol';
import { ContainerPageComponent } from 'src/app/layout/container-page/container-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutModulesGuard } from '@core/guards/aut-modules.guard';

const routes: Routes = [
  {
    path: '',
    component: ContainerPageComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
      {
        path: 'dashboard',
        data: {
          roles: [ROL],
        },
        // canActivate: [AutModulesGuard],
        loadChildren: () =>
          import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
      },
      {
        path: 'empresa',
        loadChildren: () =>
          import('./empresa/empresa.module').then((m) => m.EmpresaModule),
      },

      {
        path: 'producto',
        loadChildren: () =>
          import('./producto/producto.module').then((m) => m.ProductoModule),
      },
      {
        path: 'cuenta',
        loadChildren: () =>
          import('./cuenta/cuenta.module').then((m) => m.CuentaModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdministracionRoutingModule {}
