import { AuxProService } from './../pro-crear-editar.service';
import {
  ChangeDetectionStrategy,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  selector: 'app-variante',
  templateUrl: './variante.component.html',
  styleUrls: ['./variante.component.scss'],
})
export class VarianteComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  formGroup: FormGroup;
  listaRedesSociales: RedSocial[] = [];
  listaRedes: OpcioIn[] = [];
  permitirModificar = false;
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    public auxProService: AuxProService,
    private autenticacionService: AutenticacionService
  ) {}
  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
    this.listaRedes = this.generalService.listarDatoOpciones(
      OpcionEnum.REDES_SOCIALES
    );
  }
  add() {
    this.auxProService.agregarListaVarianteForm(null);
  }

  remove(index: number) {
    this.auxProService.removeAtListaVarianteForm(index);
  }
  changeTalla(idSeleccion, indice) {
    console.log(idSeleccion);
    console.log(indice);
    let variante =
      this.auxProService.getFormArray('listaVariante').controls[indice];
    let talla = this.generalListaProducto.listaTalla.find(
      (ta) => ta.id == idSeleccion
    );
    variante.patchValue({ codTalla: talla.codigo });
    console.log(this.auxProService.getFormArray('listaVariante').value);
  }
  changeColor(idSeleccion, indice) {
    console.log(idSeleccion);
    console.log(indice);
    let variante =
      this.auxProService.getFormArray('listaVariante').controls[indice];
    let color = this.generalListaProducto.listaColor.find(
      (ta) => ta.id == idSeleccion
    );
    variante.patchValue({ codColor: color.codigo });
    console.log(this.auxProService.getFormArray('listaVariante').value);
  }
}
