import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProductoListaDto } from '@core/models/producto.model';
import { AutenticacionService } from '@core/services/autenticacion.service';

@Component({
  selector: 'app-pro-grid',
  templateUrl: './pro-grid.component.html',
  styleUrls: ['./pro-grid.component.scss'],
})
export class ProGridComponent implements OnInit {
  @Output() destacar = new EventEmitter<ProductoListaDto>();
  @Output() ver = new EventEmitter<ProductoListaDto>();
  @Input() listaProductos: ProductoListaDto[];
  cantidadColumnas = 5;
  constructor(public autenticacionService: AutenticacionService) {}

  ngOnInit(): void {}

  changeDestacar(event: ProductoListaDto) {
    this.destacar.emit(event);
  }
  changeVer(event: ProductoListaDto) {
    this.ver.emit(event);
  }
}
