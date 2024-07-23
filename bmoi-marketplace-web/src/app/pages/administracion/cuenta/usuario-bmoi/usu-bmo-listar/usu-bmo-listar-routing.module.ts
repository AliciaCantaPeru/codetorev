import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuBmoListarComponent } from './usu-bmo-listar.component';

const routes: Routes = [{ path: '', component: UsuBmoListarComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuBmoListarRoutingModule { }
