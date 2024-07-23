import { AuxProService } from './../pro-crear-editar.service';
import {
  ChangeDetectionStrategy,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { RedSocial } from '@core/models/seller.model';
import {
  GeneralProductoDto,
  OpcioIn,
  ParametroIn,
} from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';

@Component({
  selector: 'app-personalizacion',
  templateUrl: './personalizacion.component.html',
  styleUrls: ['./personalizacion.component.scss'],
})
export class PersonalizacionComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  formGroup: FormGroup;
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    public auxProService: AuxProService
  ) {}
  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
  }

  add() {
    this.auxProService.agregarListaPersonalizacionForm(null);
  }

  remove(index: number) {
    this.auxProService.removeAtListaPersonalizacionForm(index);
  }
  personalizacion(formControl: FormGroup) {
    const personalizacion =
      this.generalListaProducto?.listaTipoPersonalizacion?.find(
        (imp) => imp.id == formControl.get('idDigtipopersonalizacion')?.value
      );
    return personalizacion ? personalizacion.descripcion : '';
  }
  obtenerListaPersonalizacion(i) {
    const listaTipo = this.auxProService.getFormArray(
      'listaPersonalizacion'
    ).controls;
    const listafilter =
      this.generalListaProducto?.listaTipoPersonalizacion?.filter((tipo) => {
        const idIgnorar = listaTipo[i].get('idDigtipopersonalizacion')?.value;
        const tipoEncontrado = listaTipo.find((cont) => {
          const idAgregado = cont.get('idDigtipopersonalizacion')?.value;
          return idAgregado == tipo.id && idIgnorar != tipo.id;
        });
        return !tipoEncontrado;
      });
    return listafilter;
  }
}
