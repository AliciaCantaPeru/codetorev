import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColBmoListarComponent } from './col-bmo-listar.component';

const routes: Routes = [{ path: '', component: ColBmoListarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColBmoListarRoutingModule { }
