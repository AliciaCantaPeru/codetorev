import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  Input,
  Output,
  EventEmitter,
} from '@angular/core';
import { AutenticacionService } from '@core/services/autenticacion.service';

@Component({
  selector: 'app-table-actions',
  templateUrl: './table-actions.component.html',
  styleUrls: ['./table-actions.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TableActionsComponent implements OnInit {
  @Input() verDescargar = false;
  @Input() verEditar = true;
  @Input() verEliminar = true;
  @Input() verFinalizar = false;
  @Input() esDispositivo = false;
  @Input() permisoEspecialEditar = false;
  @Input() textoModificar = null;
  @Output() editar = new EventEmitter<void>();
  @Output() eliminar = new EventEmitter<void>();
  @Output() finalizar = new EventEmitter<void>();
  @Output() descargar = new EventEmitter<void>();
  permitirModificar: boolean;
  constructor(private autenticacionService: AutenticacionService) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
  }

  abrirCrearEditarDialogo() {
    this.editar.emit();
  }

  confirmarEliminar() {
    this.eliminar.emit();
  }

  finalizarSolicitud() {
    this.finalizar.emit();
  }

  descargarReporte() {
    this.descargar.emit();
  }
}
