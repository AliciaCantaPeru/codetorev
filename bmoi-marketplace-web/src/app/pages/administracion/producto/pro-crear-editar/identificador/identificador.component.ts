import { FormGroup } from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';
import { AuxProService } from '../pro-crear-editar.service';
import { GeneralProductoDto } from '@core/models/general.model';

@Component({
  selector: 'app-identificador',
  templateUrl: './identificador.component.html',
  styleUrls: ['./identificador.component.scss'],
})
export class IdentificadorComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  formGroup: FormGroup;
  constructor(public auxProService: AuxProService) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
  }
}
