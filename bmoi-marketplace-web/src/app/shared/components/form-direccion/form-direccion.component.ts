import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Direccion } from '@core/models/seller.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { Opcion } from '@core/soporte/generic.model';

@Component({
  selector: 'app-form-direccion',
  templateUrl: './form-direccion.component.html',
  styleUrls: ['./form-direccion.component.scss'],
})
export class FormDireccionComponent implements OnInit {
  formGroup: FormGroup;
  dato = '';
  listaPaises: Opcion[] = [];
  listaDepartamentos: Opcion[] = [];
  listaProvincias: Opcion[] = [];
  listaDistritos: Opcion[] = [];
  direccion: Direccion;
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.generalService
      .listarPaises()
      .subscribe((resp) => (this.listaPaises = resp.dato));
  }
  setDireccion() {
    if (this.direccion) {
      const {
        direccion,
        id,
        idDepartamento,
        idDistrito,
        idPais,
        idProvincia,
        calle,
        referencia,
      } = this.direccion;
      this.formGroup.setValue({
        direccion,
        id,
        idDepartamento,
        idDistrito,
        idPais,
        idProvincia,
        calle,
        referencia,
      });
      this.getDataInicial(idPais, idDepartamento, idProvincia);
    }
  }

  getDataInicial(idPais, idDepartamento, idProvincia) {
    this.generalService
      .listarDepartamentos(idPais)
      .subscribe((resp) => (this.listaDepartamentos = resp.dato));
    this.generalService
      .listarProvincias(idDepartamento)
      .subscribe((resp) => (this.listaProvincias = resp.dato));
    this.generalService
      .listarDistritos(idProvincia)
      .subscribe((resp) => (this.listaDistritos = resp.dato));
  }
  listarDepartamento(idPais: number) {
    this.listaDepartamentos = [];
    this.listaProvincias = [];
    this.listaDistritos = [];
    this.limpiarControl('idProvincia');
    this.limpiarControl('idDepartamento');
    this.limpiarControl('idDistrito');
    this.generalService
      .listarDepartamentos(idPais)
      .subscribe((resp) => (this.listaDepartamentos = resp.dato));
  }
  listarProvincia(idDepartamento: number) {
    this.listaDistritos = [];
    this.limpiarControl('idProvincia');
    this.limpiarControl('idDistrito');
    this.generalService
      .listarProvincias(idDepartamento)
      .subscribe((resp) => (this.listaProvincias = resp.dato));
  }
  listarDistritos(idProvincia: number) {
    this.limpiarControl('idDistrito');
    this.generalService
      .listarDistritos(idProvincia)
      .subscribe((resp) => (this.listaDistritos = resp.dato));
  }
  crearForm() {
    const config: FormGroupConfig<Direccion> = {
      id: [null],
      direccion: [null, [Validators.required]],
      calle: [null, [Validators.required]],
      referencia: [null, [Validators.required]],
      idDepartamento: [null, [Validators.required]],
      idDistrito: [null, [Validators.required]],
      idPais: [null, [Validators.required]],
      idProvincia: [null, [Validators.required]],
    };
    this.formGroup = this.fb.group(config);
    this.permitirModificar = this.autenticacionService.permitirModificar();
    if (!this.permitirModificar) {
      this.formGroup.disable();
    }
    return this.formGroup;
  }

  getControl(campo: string) {
    return this.formGroup.get(campo);
  }

  limpiarControl(name) {
    this.formGroup.controls[name].setValue(null);
  }
}
