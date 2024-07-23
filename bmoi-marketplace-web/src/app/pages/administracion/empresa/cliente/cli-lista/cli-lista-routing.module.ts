import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CliListaComponent } from './cli-lista.component';

const routes: Routes = [{ path: '', component: CliListaComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CliListaRoutingModule { }
