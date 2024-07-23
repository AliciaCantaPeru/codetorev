import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProListarComponent } from './pro-listar.component';

const routes: Routes = [{ path: '', component: ProListarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProListarRoutingModule { }
