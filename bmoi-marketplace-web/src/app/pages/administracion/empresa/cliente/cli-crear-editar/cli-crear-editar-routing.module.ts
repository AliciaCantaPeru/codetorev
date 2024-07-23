import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CliCrearEditarComponent } from './cli-crear-editar.component';

const routes: Routes = [{ path: '', component: CliCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CliCrearEditarRoutingModule { }
