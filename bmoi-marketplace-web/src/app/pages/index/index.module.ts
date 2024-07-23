import { DirectivesModule } from '@shared/directives/directives.module';
import { MaterialModule } from './../../shared/material/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IndexRoutingModule } from './index-routing.module';
import { IndexComponent } from './index.component';
import { FormsModule } from '@angular/forms';
import { MatCarouselModule } from 'ng-mat-carousel';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { ToolbarIndexModule } from '@shared/components/toolbar-index/toolbar-index.module';
import { ImgSliderModule } from '@shared/components/img-slider/img-slider.module';

@NgModule({
  declarations: [IndexComponent],
  imports: [
    CommonModule,
    FormsModule,
    IndexRoutingModule,
    MaterialModule,
    DirectivesModule,
    MatCarouselModule.forRoot(),
    PerfectScrollbarModule,
    ToolbarIndexModule,
    ImgSliderModule,
  ],
})
export class IndexModule {}
