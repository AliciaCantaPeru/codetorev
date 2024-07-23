import { DirectivesModule } from '@shared/directives/directives.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImgSliderComponent } from './img-slider.component';

@NgModule({
  declarations: [ImgSliderComponent],
  imports: [CommonModule, DirectivesModule],
  exports: [ImgSliderComponent],
})
export class ImgSliderModule {}
