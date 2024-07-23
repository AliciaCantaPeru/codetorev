import { SlideToggleIconModule } from './../shared/components/slide-toggle-icon/slide-toggle-icon.module';
import { DialogConfirmacionComponent } from './../shared/components/dialog-confirmacion/dialog-confirmacion.component';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '@shared/material/material.module';
import { NgModule } from '@angular/core';
import { PagesRoutingModule } from './pages-routing.module';
import { PagesComponent } from './pages.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NZ_DATE_CONFIG } from 'ng-zorro-antd/i18n';
import { DialogAlertModule } from '@shared/components/dialog-alert/dialog-alert.module';

@NgModule({
  declarations: [PagesComponent, DialogConfirmacionComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    DialogAlertModule,
  ],
  entryComponents: [DialogConfirmacionComponent],
  providers: [
    {
      provide: NZ_DATE_CONFIG,
      useValue: {
        firstDayOfWeek: 1, // week starts on Monday (Sunday is 0)
      },
    },
  ],
})
export class PagesModule {}
