import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogAlertComponent } from './dialog-alert.component';



@NgModule({
  declarations: [DialogAlertComponent],
  imports: [
    CommonModule
  ],
  exports: [DialogAlertComponent]
})
export class DialogAlertModule { }
