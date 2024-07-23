import { AutenticacionService } from '@core/services/autenticacion.service';
import { Alert } from '@core/soporte/alert';
import { Observable, of, Subject } from 'rxjs';
import { IColumnMat } from '@core/models/util.model';
import {
  Component,
  ChangeDetectionStrategy,
  Input,
  Output,
  EventEmitter,
  OnInit,
  OnDestroy,
} from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, map, startWith } from 'rxjs/operators';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-table-options',
  templateUrl: './table-options.component.html',
  styleUrls: ['./table-options.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TableOptionsComponent implements OnInit, OnDestroy {
  @Input() verEliminar: boolean;
  @Input() verAgregar: boolean;
  @Input() conAutocomplete: boolean;
  @Input() selectColumns: IColumnMat[] = [];
  @Input() disabledDelete: boolean = false;
  @Input() multiView: boolean = false;
  @Output() filter: EventEmitter<string>;
  @Output() displayColumns: EventEmitter<Observable<string[]>>;
  @Output() agregar = new EventEmitter<void>();
  @Output() eliminar = new EventEmitter<void>();
  @Output() itemsPagination = new EventEmitter<number>();
  @Output() changeView = new EventEmitter<number>();
  myControl = new FormControl();
  options: string[] = ['Alicia', 'Isabella', 'Irena', 'Martin', 'Susi'];
  filteredOptions: Observable<string[]>;
  filterValue: string = '';
  debouncer: Subject<string> = new Subject<string>();
  countItem = 20;
  conPermisos = false;
  constructor(
    public dialogAlertService: DialogAlertService,
    private autenticacionService: AutenticacionService
  ) {
    this.displayColumns = new EventEmitter();
    this.filter = new EventEmitter();
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map((value) => this._filter(value))
    );
    this.debouncer
      .pipe(debounceTime(800))
      .subscribe((value) => this.filter.emit(value));
  }
  ngOnDestroy(): void {
    this.debouncer.unsubscribe();
  }
  ngOnInit(): void {
    this.getDisplayColumns();
    this.conPermisos = this.autenticacionService.permitirModificar();
  }
  private _filter(value: string): string[] {
    this.filterValue = value.toLowerCase();
    return this.options.filter(
      (option) => option.toLowerCase().indexOf(this.filterValue) === 0
    );
  }
  getDisplayColumns() {
    this.displayColumns.emit(
      of(
        this.selectColumns.filter((col) => col.checked).map((col) => col.column)
      )
    );
  }
  filtrar($event) {
    this.filterValue = ($event.target as HTMLInputElement).value;
    this.debouncer.next(this.filterValue);
  }
  changeItemPaginate(pagination: number) {
    this.countItem = pagination;
    this.itemsPagination.emit(this.countItem);
  }
  eliminarEmit() {
    if (this.disabledDelete) {
      this.dialogAlertService.mostrarWarning(
        'Seleccione al menos un item a eliminar'
      );
      return;
    }
    this.eliminar.emit();
  }
  changeViewDisplay(type: number) {
    this.changeView.emit(type);
  }
}
