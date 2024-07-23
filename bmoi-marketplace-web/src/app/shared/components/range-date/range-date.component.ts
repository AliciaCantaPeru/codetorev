import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { es_ES, NzI18nService } from 'ng-zorro-antd/i18n';

@Component({
  selector: 'app-range-date',
  templateUrl: './range-date.component.html',
  styleUrls: ['./range-date.component.scss'],
})
export class RangeDateComponent implements OnInit, OnChanges {
  @Output() rangeDateChange = new EventEmitter<Date[]>();
  @Input() rangeDate: Date[];
  // @Input()
  // get rangeDate(): Date[] {
  //   return this._rangeDate;
  // }
  // set rangeDate(rangeDate: Date[]) {
  //   this._rangeDate = rangeDate;
  //   this.rangeDateChange.emit(this._rangeDate);
  // }
  @Input() tipoCalendar = 'week';
  fechaInicio = null;
  fechaFin = null;
  timeDefaultValue = new Date();
  constructor(private i18n: NzI18nService) {}
  ngOnChanges(changes: SimpleChanges): void {
    this.fechaInicio =
      this.rangeDate && this.rangeDate[0] ? this.rangeDate[0] : null;
    this.fechaFin =
      this.rangeDate && this.rangeDate[1] ? this.rangeDate[1] : null;
  }

  ngOnInit(): void {
    this.i18n.setLocale(es_ES);
  }
  get validInicial() {
    return this.fechaInicio instanceof Date;
  }
  get validFin() {
    return this.fechaFin instanceof Date;
  }
  onChangeRange(result: Date, order: number): void {
    if (order == 1) {
      this.fechaFin = null;
    }
    if (result) {
      if (this.tipoCalendar === 'week' && this.fechaInicio instanceof Date) {
        this.rangeDate = [this.fechaInicio, this.fechaFin];
        this.rangeDateChange.emit(this.rangeDate);
      } else if (
        this.fechaInicio instanceof Date &&
        this.fechaFin instanceof Date
      ) {
        if (this.fechaInicio > this.fechaFin && order === 1) {
          this.fechaInicio = this.fechaFin;
          this.rangeDate = [this.fechaInicio, this.fechaFin];
          this.rangeDateChange.emit(this.rangeDate);
          return;
        }
        if (this.fechaFin < this.fechaInicio && order === 2) {
          this.fechaFin = this.fechaInicio;
          this.rangeDate = [this.fechaInicio, this.fechaFin];
          this.rangeDateChange.emit(this.rangeDate);
          return;
        }
        this.rangeDate = [this.fechaInicio, this.fechaFin];
        this.rangeDateChange.emit(this.rangeDate);
      }
    }
  }

  disabledDate = (current: Date): boolean => {
    const dateActual = new Date();
    const anioActual = dateActual.getFullYear();
    const anioSelect = current.getFullYear();
    return anioActual < anioSelect;
  };
}
