import { Opcion } from '@core/soporte/generic.model';
import {
  ChangeDetectionStrategy,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { Empresa } from '@core/models/seller.model';
import { OpcioIn, ParametroIn } from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { Validacion } from '@core/soporte/validacion';
import { merge } from 'rxjs';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-form-dato-empresa',
  templateUrl: './form-dato-empresa.component.html',
  styleUrls: ['./form-dato-empresa.component.scss'],
})
export class FormDatoEmpresaComponent implements OnInit {
  formGroup: FormGroup;
  dato: Empresa;
  listaTipoEmpresas: OpcioIn[] = [];
  listaRubro: OpcioIn[] = [];
  listaPostulante: Opcion[] = [];
  permitirModificar = false;
  maxDate: Date;
  updateValidity = false;
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.generalService
      .listarPostulante()
      .subscribe((res) => (this.listaPostulante = res.dato));
    this.listaTipoEmpresas = this.generalService.listarDatoOpciones(
      OpcionEnum.TIPO_EMPRESA
    );
    this.listaRubro = this.generalService.listarDatoOpciones(OpcionEnum.RUBRO);
  }

  crearForm() {
    const config: FormGroupConfig<Empresa> = {
      rubro: [''],
      email1: ['', [Validators.required]],
      email2: ['', [Validators.required]],
      id: [null],
      idpostulante: [null, [Validators.required]],
      nomComercial: ['', [Validators.required]],
      celular: [''],
      descripcion: [''],
      razSocial: ['', [Validators.required]],
      feciniActividades: [null],
      ruc: ['', [Validators.required]],
      telefono: ['', [Validators.required]],
      tipEmpresa: ['Persona Jurídica', [Validators.required]],
      website: [''],
    };
    this.formGroup = this.fb.group(config);
    this.permitirModificar = this.autenticacionService.permitirModificar();
    merge(
      this.formGroup.controls.email1.valueChanges,
      this.formGroup.controls.email2.valueChanges
    )
      .pipe(delay(100))
      .subscribe((res) => {
        if (!this.updateValidity) {
          this.ValidarRepeticion();
        }
        if (this.updateValidity) {
          this.updateValidity = false;
        }
      });
    if (!this.permitirModificar) {
      this.formGroup.disable();
    }
    return this.formGroup;
  }
  private ValidarRepeticion() {
    const controlCel1: AbstractControl = this.formGroup.controls.email1;
    const controlCel2: AbstractControl = this.formGroup.controls.email2;
    let errors: ValidationErrors = null;
    if (controlCel1?.value.trim() == controlCel2?.value.trim()) {
      errors = {
        valueNotEqual: 'El Email 1 registrado no debe ser el mismo del Email 2',
      };
    }
    controlCel2.setErrors(errors);
    if (controlCel2?.value.trim() != '' && controlCel2.errors == null) {
      controlCel2.setValidators([Validators.email]);
      controlCel2.updateValueAndValidity();
      this.updateValidity = true;
    }
  }

  setDataEmpresa() {
    console.log(this.dato);
    if (this.dato) {
      const {
        email1,
        email2,
        id,
        nomComercial,
        razSocial,
        rubro,
        ruc,
        telefono,
        feciniActividades,
        website,
        tipEmpresa,
        idpostulante,
        celular,
        descripcion,
      } = this.dato;
      this.formGroup.setValue({
        email1,
        email2,
        id,
        nomComercial,
        razSocial,
        rubro,
        ruc,
        telefono,
        feciniActividades,
        idpostulante,
        website,
        tipEmpresa,
        celular,
        descripcion,
      });
    }
  }

  getControl(campo: string) {
    return this.formGroup.get(campo);
  }
  soloNumero($event) {
    return Validacion.validarSoloNumeros($event);
  }
}
