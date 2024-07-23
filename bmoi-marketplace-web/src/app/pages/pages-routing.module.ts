import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthAdminGuard } from '@core/guards/auth-admin.guard';
import { AuthLoginGuard } from '@core/guards/auth-login.guard';
import { ResolveGeneralService } from '@core/resolve/resolve-general-service';

const pageRouter: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  {
    path: '',
    canActivate: [AuthLoginGuard],
    loadChildren: () =>
      import('./seguridad/seguridad.module').then((m) => m.SeguridadModule),
  },
  {
    path: '',
    canActivate: [AuthAdminGuard],
    resolve: {
      employees: ResolveGeneralService,
    },
    loadChildren: () =>
      import('./administracion/administracion.module').then(
        (m) => m.AdministracionModule
      ),
  },
  {
    path: '',
    loadChildren: () =>
      import('./index/index.module').then((m) => m.IndexModule),
  },
  {
    path: '',
    loadChildren: () =>
      import('./descargas/descargas.module').then((m) => m.DescargasModule),
  },
  { path: '**', redirectTo: 'index', pathMatch: 'full' },
];
@NgModule({
  imports: [RouterModule.forRoot(pageRouter)],
  exports: [RouterModule],
})
export class PagesRoutingModule {}
