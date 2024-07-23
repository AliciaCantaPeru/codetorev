import { Component, OnInit, Input } from '@angular/core';
import {
  AbstractControlDirective,
  AbstractControl,
  FormArray,
} from '@angular/forms';
import { MatChipList } from '@angular/material/chips';

@Component({
  selector: 'app-validacion',
  templateUrl: './validacion.component.html',
  styleUrls: ['./validacion.component.scss'],
})
export class ValidacionComponent {
  this = this;
  private readonly ERROR_MENSAJES = {
    required: () => 'Este campo es requerido',
    minlength: (params) =>
      'Se requiere mínimo ' + params.requiredLength + ' caracteres',
    maxlength: (params) =>
      'Se acepta máximo ' + params.requiredLength + ' caracteres.',
    pattern: (params) =>
      'Formato incorrecto. Formato correcto ' + this.patternCorrecto,
    // params.requiredPattern,
    email: () => 'Ingrese un e-mail válido',
    unico: (params) => params.message,
    valueNotEqual: (params) => params,
    // 'countryCity': (params) => params.message,Formato incorrecto. Ejemplo '#fff' o '#ffffff' htm
    emailRepeat: () =>
      'El email ya se  encuentra asociado a una cuenta, intente con otro correo electrónico',
    cantidadLista: (params) => this.cantidadLista,
    minNumber: (params) =>
      'Se requiere mínimo ' + this.minCantidad + ' producto.',
    // 'telephoneNumber': (params) => params.message
  };

  @Input() control: AbstractControlDirective | AbstractControl | FormArray;
  @Input() patternCorrecto: string;
  @Input() cantidadLista: string;
  @Input() minCantidad: number;
  @Input() chipList: MatChipList;
  constructor() {}
  verMensajeValidacion(): boolean {
    const error =
      this.control &&
      this.control.errors &&
      (this.control.dirty || this.control.touched);
    if (this.chipList) {
      this.chipList.errorState = error;
    }
    return error;
  }

  listarErrores(): string[] {
    return Object.keys(this.control.errors).map((field) =>
      this.getMessage(field, this.control.errors[field])
    );
  }

  private getMessage(type: string, params: any) {
    return this.ERROR_MENSAJES[type](params);
  }
}
