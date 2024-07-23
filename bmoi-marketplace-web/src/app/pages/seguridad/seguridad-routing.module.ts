import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SeguridadComponent } from './seguridad.component';

const routes: Routes = [
  {
    path: '',
    component: SeguridadComponent,
    children: [
      {
        path: 'login',
        loadChildren: () =>
          import('./autenticacion/autenticacion.module').then(
            (m) => m.AutenticacionModule
          ),
      },
      {
        path: 'recuperar-clave/:tipoUsuario',
        loadChildren: () =>
          import('./recuperar-clave/recuperar-clave.module').then(
            (m) => m.RecuperarClaveModule
          ),
      },
      {
        path: 'cambiar-clave',
        loadChildren: () =>
          import('./cambiar-clave/cambiar-clave.module').then(
            (m) => m.CambiarClaveModule
          ),
      },
      {
        path: 'activar-cuenta',
        loadChildren: () =>
          import('./activar-cuenta/activar-cuenta.module').then(
            (m) => m.ActivarCuentaModule
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SeguridadRoutingModule {}
