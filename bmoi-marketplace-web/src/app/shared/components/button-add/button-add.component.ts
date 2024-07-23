import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { AutenticacionService } from '@core/services/autenticacion.service';

@Component({
  selector: 'app-button-add',
  templateUrl: './button-add.component.html',
  styleUrls: ['./button-add.component.scss'],
})
export class ButtonAddComponent implements OnInit {
  @Input() permisoEspecialEditar = false;
  @Output() agregar = new EventEmitter<void>();
  permitirModificar = false;
  constructor(private autenticacionService: AutenticacionService) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    if (this.permisoEspecialEditar) {
      this.permisoEspecialEditar =
        this.autenticacionService.permitirModificar();
    }
  }
}
