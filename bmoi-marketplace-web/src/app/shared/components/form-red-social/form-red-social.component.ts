import {
  ChangeDetectionStrategy,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedSocial } from '@core/models/seller.model';
import { OpcioIn, ParametroIn } from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';

@Component({
  selector: 'app-form-red-social',
  templateUrl: './form-red-social.component.html',
  styleUrls: ['./form-red-social.component.scss'],
})
export class FormRedSocialComponent implements OnInit {
  @Input() esPerfil: boolean;
  formGroup: FormGroup;
  listaRedesSociales: RedSocial[] = [];
  listaRedes: OpcioIn[] = [];
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.listaRedes = this.generalService.listarDatoOpciones(
      OpcionEnum.REDES_SOCIALES
    );
  }

  setRedSocial() {
    this.listaRedesSociales.forEach((redSocial) => {
      this.getFormArray().push(this.nuevaRedSocial(redSocial));
    });
  }
  crearForm() {
    this.formGroup = this.fb.group({
      redes: this.fb.array([], Validators.required),
    });
  }
  getFormArray() {
    return this.formGroup.get('redes') as FormArray;
  }
  nuevaRedSocial(redSocial: RedSocial): FormGroup {
    const config: FormGroupConfig<RedSocial> = {
      id: [null],
      nombre: [null, [Validators.required]],
      url: ['', [Validators.required]],
    };
    let formGroup = this.fb.group(config);
    if (redSocial != null) {
      const { id, nombre, url } = redSocial;
      formGroup.setValue({ id, nombre, url });
    }
    this.permitirModificar = this.autenticacionService.permitirModificar();
    if (this.esPerfil || !this.permitirModificar) {
      formGroup.disable();
    }
    return formGroup;
  }

  agregarRedSocial() {
    if (this.getFormArray().length !== 0 && this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }
    this.getFormArray().push(this.nuevaRedSocial(null));
  }

  quitarRedSocial(index: number) {
    this.getFormArray().removeAt(index);
  }

  getControl(campo: string) {
    return this.formGroup.get(campo);
  }
}
