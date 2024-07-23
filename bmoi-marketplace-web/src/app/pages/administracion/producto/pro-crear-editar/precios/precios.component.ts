import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { GeneralProductoDto, ParametroIn } from '@core/models/general.model';
import { GeneralService } from '@core/services/general.service';
import { Parametro } from '@core/soporte/enums';
import { AuxProService } from '../pro-crear-editar.service';

@Component({
  selector: 'app-precios',
  templateUrl: './precios.component.html',
  styleUrls: ['./precios.component.scss'],
})
export class PreciosComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  formGroup: FormGroup;
  listaImpuesto: ParametroIn[] = [];
  constructor(
    public auxProService: AuxProService,
    private generalService: GeneralService
  ) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
    this.listarImpuestos();
  }
  listarImpuestos() {
    this.listaImpuesto = this.generalService.listarParamatros(
      Parametro.PRODUCTO_IMPUESTOS
    );
  }

  get montoPorcentaje() {
    const impuesto = this.listaImpuesto?.find(
      (imp) => imp.tipo == this.auxProService?.getControl('impuesto')?.value
    );
    return impuesto ? impuesto.valor : '';
  }
}
