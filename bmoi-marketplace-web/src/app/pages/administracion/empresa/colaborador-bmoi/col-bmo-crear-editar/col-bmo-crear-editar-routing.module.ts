import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColBmoCrearEditarComponent } from './col-bmo-crear-editar.component';

const routes: Routes = [{ path: '', component: ColBmoCrearEditarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColBmoCrearEditarRoutingModule { }
