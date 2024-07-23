import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RangeDateComponent } from './range-date.component';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [RangeDateComponent],
  imports: [CommonModule, FormsModule, FlexLayoutModule, NzDatePickerModule],
  exports: [RangeDateComponent],
})
export class RangeDateModule {}
