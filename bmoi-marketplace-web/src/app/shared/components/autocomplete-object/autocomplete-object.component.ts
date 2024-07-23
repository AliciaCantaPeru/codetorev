import {
  Component,
  ChangeDetectionStrategy,
  Input,
  Output,
  EventEmitter,
  OnChanges,
} from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Opcion } from '@core/soporte/generic.model';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-autocomplete-object',
  templateUrl: './autocomplete-object.component.html',
  styleUrls: ['./autocomplete-object.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AutocompleteObjectComponent implements OnChanges {
  @Output() controlChange = new EventEmitter<FormControl>();
  _control: FormControl;
  @Input()
  get control(): FormControl {
    return this._control;
  }
  set control(formCliente: FormControl) {
    if (this.seleccionado) {
      return;
    }
    this._control = formCliente;
    this.controlChange.emit(this._control);
  }
  @Input() values: Opcion[] = [];
  @Input() label = '';
  @Input() placeholder = '';
  @Output() seleccionChange = new EventEmitter<number | string>();
  valuesFilter: Observable<Opcion[]>;
  seleccionado = false;

  constructor() {}

  ngOnChanges(): void {
    setTimeout(() => {
      this.filterValues();
    }, 500);
  }

  filterValues() {
    this.valuesFilter = this.control.valueChanges.pipe(
      startWith<string | Opcion>(''),
      map((value) => (typeof value === 'string' ? value : value?.nombre)),
      map((value: string) => this.filter(value))
    );
  }
  private filter(value): Opcion[] {
    if (!value || value === '') {
      return this.values;
    }
    const filterValue = value.toLowerCase();
    this.seleccionado = false;
    return this.values.filter(
      (option) => option.nombre.toLowerCase().indexOf(filterValue) === 0
    );
  }
  verificarSeleccion() {
    setTimeout(() => {
      if (!this.seleccionado) {
        this.control.setValue(null);
      }
    }, 500);
  }
  verDato(lista: Opcion[]): (idCliente: number) => string {
    return (idCliente: number) => {
      const find = lista?.find((option) => option.id === idCliente);
      return find ? find.nombre : '';
    };
  }
  selected(option: MatAutocompleteSelectedEvent) {
    this.seleccionado = true;
  }
}
