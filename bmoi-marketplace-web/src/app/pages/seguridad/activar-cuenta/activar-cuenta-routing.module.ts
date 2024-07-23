import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivarCuentaComponent } from './activar-cuenta.component';

const routes: Routes = [{ path: '', component: ActivarCuentaComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivarCuentaRoutingModule { }
