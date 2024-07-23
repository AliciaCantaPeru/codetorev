import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  Input,
} from '@angular/core';
import { SlideContentDto } from '@core/models/content-web.model';

@Component({
  selector: 'app-img-slider',
  templateUrl: './img-slider.component.html',
  styleUrls: ['./img-slider.component.scss'],
  animations: [
    trigger('flyInOut', [
      state('in', style({ opacity: 1, transform: 'translateX(0)' })),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'translateX(+100%)',
        }),
        animate('0.5s ease-in'),
      ]),
    ]),
  ],
})
export class ImgSliderComponent implements OnInit {
  @Input() listaImagenes: SlideContentDto[];
  @Input() interval = 5000;
  paginaActual = 0;
  constructor() {}

  ngOnInit(): void {
    setInterval(() => {
      this.iterarListaImagenes();
    }, this.interval);
  }

  iterarListaImagenes() {
    if (this.paginaActual + 1 >= this.listaImagenes.length) {
      this.paginaActual = 0;
    } else {
      this.paginaActual++;
    }
    console.log(this.paginaActual);
  }
}
