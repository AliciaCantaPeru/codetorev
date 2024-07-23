import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  Input,
} from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Persona } from '@core/models/seller.model';
import { OpcioIn, ParametroIn } from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { Alert } from '@core/soporte/alert';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { Util } from '@core/soporte/util';
import { Validacion } from '@core/soporte/validacion';
import { merge } from 'rxjs';
import {
  DialogConfirmacionComponent,
  IDataConfirm,
} from '../dialog-confirmacion/dialog-confirmacion.component';
import { switchMap } from 'rxjs/operators';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { SellerService } from '@core/services/seller.service';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-form-contacto',
  templateUrl: './form-contacto.component.html',
  styleUrls: ['./form-contacto.component.scss'],
})
export class FormContactoComponent implements OnInit {
  @Input() tipo = 1;
  @Input() esPerfil: boolean;
  @Input() idSeller: number;
  formGroup: FormGroup;
  dato = '';
  listaPersonas: Persona[] = [];
  editar = false;
  listaTipoDocumento: OpcioIn[] = [];
  listaSexo: OpcioIn[] = [];
  listaCargo: OpcioIn[] = [];
  ordenSeleccionado = 0;
  maxDate: Date;
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    public dialogAlertService: DialogAlertService,
    private generalService: GeneralService,
    private sellerService: SellerService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.listaTipoDocumento = this.generalService.listarDatoOpciones(
      OpcionEnum.TIPO_DOCUMENTO
    );
    this.listaCargo = this.generalService.listarDatoOpciones(OpcionEnum.CARGO);
    this.listaSexo = this.generalService.listarDatoOpciones(OpcionEnum.SEXO);
    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear() - 18);
    // this.agregarPersona();
  }

  crearForm() {
    this.formGroup = this.fb.group({
      personas: this.fb.array([], Validators.required),
    });
    this.permitirModificar = this.autenticacionService.permitirModificar();
    if (this.permitirModificar) {
      this.formGroup.disable();
    }
    return this.formGroup;
  }

  getControl(campo: string) {
    return this.formGroup.get(campo);
  }

  soloNumero($event) {
    return Validacion.validarSoloNumeros($event);
  }

  private setPersonaToFormGroup(
    persona: Persona,
    form: FormGroup | AbstractControl
  ) {
    const {
      celular1,
      celular2,
      email,
      sexo,
      id,
      // idSeller,
      priNombre,
      segNombre,
      numDocumento,
      priApellido,
      segApellido,
      tipDocumento,
      cargo,
      orden,
      fecNacimiento: nacimiento,
    } = persona;
    const cntPrincipal = persona.cntPrincipal === 1 ? true : false;
    const fecNacimiento = new Date(nacimiento + 'T00:00:00');
    form.setValue({
      priNombre,
      segNombre,
      priApellido,
      segApellido,
      celular1,
      celular2,
      id,
      // idSeller,
      email,
      sexo,
      numDocumento,
      tipDocumento,
      cargo,
      cntPrincipal,
      orden,
      fecNacimiento,
    });
  }

  setEmailValidacion(formGroup: FormGroup) {
    const control: AbstractControl = formGroup.controls.email;
    let errors: ValidationErrors;
    if (control.errors === null) {
      errors = null;
      const email = formGroup.get('email').value;
      const ordenForm = formGroup.get('orden').value;
      const id = formGroup.get('id').value;
      const personaEncontrada = this.getFormArray().controls.find(
        (per) =>
          per.get('email').value === email &&
          per.get('orden').value !== ordenForm
      );
      if (personaEncontrada) {
        errors = { emailRepeat: true };
        formGroup.get('email').setErrors(errors);
      } else {
        this.generalService
          .correoUnico(email, id == null ? 0 : id, 'SELLER')
          .subscribe((res) => {
            if (res.dato) {
              errors = { emailRepeat: true };
              formGroup.get('email').setErrors(errors);
            }
          });
      }
    }
  }

  visibleBoton() {
    return this.editar;
  }

  setListaPersonas() {
    this.listaPersonas.forEach((persona, index) => {
      persona.orden = index + 1;
      this.getFormArray().push(this.agregarPersonaForm(persona));
    });
    if (this.listaPersonas.length != 0) {
      if (this.esPerfil) {
        const personaLogeada = this.listaPersonas.find(
          (persona) =>
            persona.id ===
            this.autenticacionService?.getCurrentUser()?.idSellerPersona
        );
        this.ordenSeleccionado = personaLogeada ? personaLogeada.orden : 1;
      } else {
        const personaPrincipal = this.listaPersonas.find(
          (persona) => persona.cntPrincipal === 1
        );
        this.ordenSeleccionado = personaPrincipal ? personaPrincipal.orden : 1;
      }
    }
  }

  setOnePersona() {
    const persona = this.listaPersonas[0];
    persona.orden = 1;
    //console.log('persona,', persona);
    this.setPersonaToFormGroup(persona, this.getFormArray().controls[0]);
  }

  getFormArray() {
    return this.formGroup.get('personas') as FormArray;
  }

  agregarPersonaForm(persona: Persona) {
    const config: FormGroupConfig<Persona> = {
      email: ['', Validators.compose([Validators.required, Validators.email])],
      id: [null],
      // idSeller: [null],
      fecNacimiento: ['', [Validators.required]],
      sexo: ['', [Validators.required]],
      priNombre: ['', [Validators.required]],
      segNombre: [''],
      numDocumento: ['', [Validators.required]],
      priApellido: ['', [Validators.required]],
      segApellido: ['', [Validators.required]],
      tipDocumento: ['', [Validators.required]],
      cargo: ['', [Validators.required]],
      celular1: ['', [Validators.required]],
      celular2: [''],
      cntPrincipal: [null],
      orden: [null],
    };
    let formGroup = this.fb.group(config);
    if (persona != null) {
      this.setPersonaToFormGroup(persona, formGroup);
    } else {
      let orden =
        this.getFormArray().controls.length != 0
          ? Math.max(
              ...this.getFormArray().controls.map(
                (per) => per.get('orden').value
              )
            ) + 1
          : 1;
      formGroup.patchValue({ orden: orden });
      this.ordenSeleccionado = orden;
    }
    this.permitirModificar = this.autenticacionService.permitirModificar();
    if (this.esPerfil || !this.permitirModificar) {
      formGroup.disable();
    }
    formGroup.controls.email.valueChanges.subscribe((control) =>
      this.setEmailValidacion(formGroup)
    );
    merge(
      formGroup.controls.celular1.valueChanges,
      formGroup.controls.celular2.valueChanges
    ).subscribe((res) => {
      const controlCel1: AbstractControl = formGroup.controls.celular1;
      const controlCel2: AbstractControl = formGroup.controls.celular2;
      this.ValidarRepeticion(controlCel2, controlCel1);
    });
    return formGroup;
  }

  private ValidarRepeticion(
    controlCel2: AbstractControl,
    controlCel1: AbstractControl
  ) {
    let errors: ValidationErrors = null;
    if (
      controlCel2?.value.trim() != '' &&
      controlCel2?.value.trim().length < 9
    ) {
      errors = {
        minlength: {
          actualLength: controlCel2?.value.trim().length,
          requiredLength: 9,
        },
      };
    }
    if (
      controlCel1?.value.trim() == controlCel2?.value.trim() &&
      controlCel2?.value.trim().length >= 9
    ) {
      errors = {
        valueNotEqual:
          'El numero ingresado no debe ser el mismo del Teléfono móvil 1',
      };
    }
    controlCel2.setErrors(errors);
    return errors;
  }

  agregarPersona() {
    if (this.getFormArray().length !== 0 && this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }
    this.getFormArray().push(this.agregarPersonaForm(null));
  }

  editarPersona(index: number) {
    if (this.formGroup.invalid) {
      this.getFormArray().removeAt(this.ordenSeleccionado - 1);
    }
    this.ordenSeleccionado = index + 1;
  }

  cancelar(index) {
    this.getFormArray().removeAt(index);
    if (this.getFormArray().length != 0) {
      const personaPrincipal = this.getFormArray().controls.find(
        (persona) => persona.get('cntPrincipal').value === 1
      );
      this.ordenSeleccionado = personaPrincipal
        ? personaPrincipal.get('orden').value
        : 1;
    }
  }

  eliminar(index) {
    if (this.getFormArray().controls.length === 1) {
      this.dialogAlertService.mostrarWarning(
        'Se requiere el registro mínimo de un contacto'
      );
      return;
    }
    const data: IDataConfirm = {
      titulo: 'Eliminar contacto',
      descripcion: '¿Seguro que desea eliminar este registro?',
      labelOk: 'Eliminar',
    };
    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.getFormArray().removeAt(index);
      }
    });
  }
  resetearCuenta() {
    this.sellerService
      .resetearCuenta(
        this.autenticacionService.getCurrentUser().idSellerPersona,
        this.idSeller
      )
      .subscribe((res) =>
        this.dialogAlertService.mostrarConfirmacion(
          'Se envió un correo con las nuevas credenciales'
        )
      );
  }
  changeFecha(event: MatDatepickerInputEvent<Date>, item, inputFecha) {
    inputFecha.blur();
    item.setValue(null);
  }
}
