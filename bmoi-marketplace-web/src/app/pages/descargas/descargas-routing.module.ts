import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DescargasComponent } from './descargas.component';

const routes: Routes = [{ path: 'descargas', component: DescargasComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DescargasRoutingModule {}
