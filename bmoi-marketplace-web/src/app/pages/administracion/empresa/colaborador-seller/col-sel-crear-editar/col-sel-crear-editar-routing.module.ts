import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColSelCrearEditarComponent } from './col-sel-crear-editar.component';

const routes: Routes = [{ path: '', component: ColSelCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColSelCrearEditarRoutingModule { }
