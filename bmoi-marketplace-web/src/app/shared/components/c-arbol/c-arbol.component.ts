import {
  Component,
  Input,
  Output,
  EventEmitter,
  ViewEncapsulation,
  AfterContentInit,
  ContentChildren,
  QueryList,
  TemplateRef,
  OnInit,
  ChangeDetectorRef,
} from '@angular/core';
import { Util } from '@core/soporte/util';
import { TemplateDirective } from '@shared/directives/template.directive';

@Component({
  selector: 'vista-c-arbol',
  templateUrl: './c-arbol.component.html',
  styleUrls: ['./c-arbol.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class CArbolComponent implements AfterContentInit {
  @Input() data: ArbolData<any>[];
  @Input() verSeleccionTodos = false;
  @Input() seleccionUnica = false;
  @Input() modoSeleccion; // checkBox
  @Input() tipoSeleccion = 'dependiente'; // dependiente (selecion del padre e hijos) e independiente seleccion individual y doble click para seleccion total
  @Output() changeChecked: EventEmitter<ArbolData<any>[]> = new EventEmitter();
  @Output() draggStar: EventEmitter<ArbolData<any>> = new EventEmitter();
  @Output() itemClick: EventEmitter<ArbolData<any>> = new EventEmitter();
  seleccionarTodos: boolean;
  itemsSelect: ArbolData<string>[] = [];
  refrescar = false;
  iconoSeleccionTodos = 'fa fa-square-o';
  @ContentChildren(TemplateDirective) templates: QueryList<any>;
  public itemTemplate: TemplateRef<any>;

  constructor(private cd: ChangeDetectorRef) {}

  emitChangeChecked(event: ArbolData<any>) {
    if (this.seleccionUnica) {
      this.refrescar = true;
      this.data.forEach((dat) => {
        dat.hijos.forEach(
          (h) => (h.checked = event.data == h.data ? event.checked : false)
        );
      });
      setTimeout(() => {
        this.changeChecked.emit(this.data);
        this.refrescar = false;
      }, 10);
    }
    this.obtenerIconoCheckTodos();
  }

  obtenerIconoCheckTodos() {
    const seleccionParcial = Util.seleccionParcial(this.data);
    const seleccionTotal = Util.seleccionTotal(this.data);
    if (!seleccionTotal && seleccionParcial) {
      this.iconoSeleccionTodos = 'fa fa-minus-square';
      this.seleccionarTodos = false;
      return;
    }
    if (seleccionTotal && seleccionParcial) {
      this.seleccionarTodos = true;
    } else {
      this.seleccionarTodos = false;
    }
    this.iconoSeleccionTodos = this.seleccionarTodos
      ? 'fa fa-check-square'
      : 'fa fa-square-o';
  }

  onClick_toggleSeleccionTodos() {
    this.seleccionarTodos = !this.seleccionarTodos;
    this.refrescar = true;
    this.seleccionarData(this.data);
    setTimeout(() => (this.refrescar = false), 10);
    this.obtenerIconoCheckTodos();
    this.changeChecked.emit(this.data);
  }

  private seleccionarData(hijos: ArbolData<any>[]): ArbolData<any>[] {
    hijos.forEach((d) => {
      d.checked = this.seleccionarTodos;
      if (d.hijos && d.hijos.length > 0) {
        d.hijos = this.seleccionarData(d.hijos);
      }
    });
    return hijos;
  }

  ngAfterContentInit() {
    this.templates.forEach((item) => {
      switch (item.getType()) {
        case 'item':
          this.itemTemplate = item.template;
          break;

        default:
          this.itemTemplate = item.template;
          break;
      }
    });
  }
  emitItemClick($event) {
    this.itemClick.emit($event);
  }
}

export interface ArbolData<Tipo> {
  label: string;
  iconoShow?: string;
  iconoHide?: string;
  icono?: string;
  data?: Tipo;
  hijos?: ArbolData<Tipo>[];
  seleccionable?: boolean;
  desplegar?: boolean;
  checked?: boolean;
  iconoCheck?: string;
  ocultarHijos?: boolean;
}
