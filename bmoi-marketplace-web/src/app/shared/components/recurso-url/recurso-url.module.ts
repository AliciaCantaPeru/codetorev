import { DirectivesModule } from '@shared/directives/directives.module';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecursoUrlComponent } from './recurso-url.component';
import { FormsModule } from '@angular/forms';
import { NzUploadModule } from 'ng-zorro-antd/upload';

@NgModule({
  declarations: [RecursoUrlComponent],
  imports: [
    CommonModule,
    FormsModule,
    NzUploadModule,
    MaterialModule,
    DirectivesModule,
  ],
  exports: [RecursoUrlComponent],
})
export class RecursoUrlModule {}
