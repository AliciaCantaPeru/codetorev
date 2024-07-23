import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  TemplateRef,
  ChangeDetectorRef,
} from '@angular/core';
import { Util } from '@core/soporte/util';
import { ArbolData } from '../c-arbol.component';

@Component({
  selector: 'vista-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css'],
})
export class ItemComponent implements OnInit {
  @Input() itemTemplate: TemplateRef<any>;
  @Input() data: ArbolData<any>;
  @Input() indice: number;
  @Input() seleccionUnica = false;
  @Input() modoSeleccion: string;
  @Input() tipoSeleccion: string;
  @Output() indiceSeleccionado: EventEmitter<void> = new EventEmitter();
  @Output() changeChecked: EventEmitter<ArbolData<any>> = new EventEmitter();
  @Output() itemClick: EventEmitter<ArbolData<any>> = new EventEmitter();
  verIconoDesplegar = 'hidden';
  verIconoLabel = 'hidden';
  iconoChec = 'fa fa-square-o';

  constructor(private cd: ChangeDetectorRef) {}

  ngOnInit() {
    this.verIcono();
  }

  verHijos() {
    this.data.desplegar = !this.data.desplegar;
  }

  obtenerIconoToggle(): String {
    if (this.data.iconoHide && this.data.iconoShow) {
      return this.data.desplegar && this.data.hijos
        ? this.data.iconoShow
        : this.data.iconoHide;
    } else {
      return !this.data.desplegar ? 'fa fa-caret-right' : 'fa fa-caret-down';
    }
  }

  private verIcono(): void {
    this.verIconoDesplegar =
      this.data.hijos && this.data.hijos.length > 0
        ? !this.data.ocultarHijos
          ? 'visible'
          : 'hidden'
        : 'hidden';
    this.verIconoLabel = this.data.icono ? 'visible' : 'hidden';
    this.data.iconoCheck = this.data.checked
      ? 'fa fa-check-square'
      : 'fa fa-square-o';
  }

  obtenerIcono(): string {
    return this.data.icono ? this.data.icono : 'fa fa-file-o';
  }

  seleccionable() {
    return this.data.seleccionable ? 'ui-seleccionable' : 'no-class';
  }

  obtenerIconoCheck(numeroClick: number) {
    // if (this.modoSeleccion !== 'checkBox') {
    //   this.verHijos();
    //   return;
    // }
    if (!this.data.seleccionable) {
      this.verHijos();
      return;
    }
    if (this.seleccionUnica) {
      return;
    }
    const conHijos: boolean = this.data.hijos && this.data.hijos.length > 0;
    if (
      (this.tipoSeleccion === 'dependiente' || numeroClick === 2) &&
      conHijos
    ) {
      const seleccionParcialTrue = this.data.hijos.some((d) => d.checked);
      const seleccionParcialFalse = this.data.hijos.some((d) => !d.checked);
      this.data.checked =
        seleccionParcialTrue && seleccionParcialFalse
          ? true
          : !seleccionParcialTrue
          ? true
          : false;
      this.seleccionarData(this.data.hijos, this.data.checked);
    } else {
      this.data.checked = !this.data.checked;
    }

    if (this.tipoSeleccion === 'dependiente') {
      this.data.iconoCheck = this.data.checked
        ? 'fa fa-check-square'
        : 'fa fa-square-o';
    } else {
      this.data.iconoCheck = this.data.checked
        ? conHijos && numeroClick === 1
          ? 'fa fa-circle'
          : 'fa fa-check-square'
        : 'fa fa-square-o';
    }

    if (this.tipoSeleccion === 'dependiente' || numeroClick === 2) {
      this.indiceSeleccionado.emit();
    }
    this.changeChecked.emit(this.data);
    this.itemClick.emit(this.data);
  }

  emitIndiceSeleccionado(event) {
    if (this.seleccionUnica) {
      return;
    }
    const seleccionParcial = Util.seleccionParcial(this.data.hijos);
    const seleccionTotal = Util.seleccionTotal(this.data.hijos);
    if (!seleccionTotal && seleccionParcial) {
      this.data.iconoCheck = 'fa fa-minus-square';
      this.data.checked = true;
      this.indiceSeleccionado.emit();
      return;
    }
    if (seleccionParcial) {
      this.data.checked = true;
    } else {
      this.data.checked = false;
    }
    this.data.iconoCheck = this.data.checked
      ? 'fa fa-check-square'
      : 'fa fa-square-o';
    this.indiceSeleccionado.emit();
  }

  emitChangeChecked(event: ArbolData<any>) {
    console.log('recibo del hijo', event);
    console.log('padre del hijo', this.data);
    this.data.hijos.forEach((dat) => {
      dat.checked = event.data == dat.data ? event.checked : false;
    });
    this.changeChecked.emit(event);
  }

  emitItemClick($event) {
    this.itemClick.emit($event);
  }

  listarHijos(): boolean {
    if (this.data) {
      if (!this.data.ocultarHijos && this.data.desplegar && this.data.hijos) {
        return true;
      }
    } else {
      return false;
    }
  }

  private seleccionarData(
    hijos: ArbolData<any>[],
    checkPadre: boolean
  ): ArbolData<any>[] {
    hijos.forEach((d) => {
      d.checked = checkPadre;
      d.iconoCheck = d.checked ? 'fa fa-check-square' : 'fa fa-square-o';
      if (d.hijos && d.hijos.length > 0) {
        d.hijos = this.seleccionarData(d.hijos, d.checked);
      }
    });
    return hijos;
  }
}
