import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColSelListarComponent } from './col-sel-listar.component';

const routes: Routes = [{ path: '', component: ColSelListarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColSelListarRoutingModule { }
