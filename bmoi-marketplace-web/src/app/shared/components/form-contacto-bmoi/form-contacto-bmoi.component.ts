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
import { ColaboradorBmoiMant } from '@core/models/colaborador-bmoi.model';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-form-contacto-bmoi',
  templateUrl: './form-contacto-bmoi.component.html',
  styleUrls: ['./form-contacto-bmoi.component.scss'],
})
export class FormContactoBmoiComponent implements OnInit {
  @Input() esPerfil: boolean;
  formGroup: FormGroup;
  dato = '';
  listaPersonas: ColaboradorBmoiMant[] = [];
  editar = false;
  listaTipoDocumento: OpcioIn[] = [];
  listaSexo: OpcioIn[] = [];
  ordenSeleccionado = 0;
  maxDate: Date;
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    public dialogAlertService: DialogAlertService,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.listaTipoDocumento = this.generalService.listarDatoOpciones(
      OpcionEnum.TIPO_DOCUMENTO
    );
    this.listaSexo = this.generalService.listarDatoOpciones(OpcionEnum.SEXO);
    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear() - 18);
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
    persona: ColaboradorBmoiMant,
    form: FormGroup | AbstractControl
  ) {
    const {
      id,
      nombres,
      priApellido,
      segApellido,
      sexo,
      tipDocumento,
      numDocumento,
      email,
      celular,
      codigopais,
      fecNacimiento: nacimiento,
      orden,
    } = persona;
    const fecNacimiento = new Date(nacimiento + 'T00:00:00');
    form.setValue({
      id,
      nombres,
      priApellido,
      segApellido,
      sexo,
      tipDocumento,
      numDocumento,
      email,
      celular,
      codigopais,
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
          .correoUnico(email, id == null ? 0 : id, 'BMOI')
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
        this.ordenSeleccionado = 1;
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

  agregarPersonaForm(persona: ColaboradorBmoiMant) {
    const config: FormGroupConfig<ColaboradorBmoiMant> = {
      celular: ['', [Validators.required]],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      id: [null],
      fecNacimiento: ['', [Validators.required]],
      sexo: ['', [Validators.required]],
      nombres: ['', [Validators.required]],
      numDocumento: ['', [Validators.required]],
      priApellido: ['', [Validators.required]],
      segApellido: ['', [Validators.required]],
      tipDocumento: ['', [Validators.required]],
      codigopais: [null, [Validators.required]],
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
    return formGroup;
  }

  verSeleccionCtaPrincipal(indiceControl: number) {
    const users = this.getFormArray().controls;
    let indexCuentaPrincipal = -1;
    users.forEach((user, index) => {
      const ctaPrincipal = user.get('cntPrincipal').value;
      if (ctaPrincipal) {
        indexCuentaPrincipal = index;
      }
    });
    if (indexCuentaPrincipal === -1) {
      return true;
    }
    return indiceControl === indexCuentaPrincipal;
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
}
