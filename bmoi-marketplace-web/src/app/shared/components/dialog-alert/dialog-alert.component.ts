import { Subscription } from 'rxjs';
import {
  IDataConfirm,
  DialogConfirmacionComponent,
} from './../dialog-confirmacion/dialog-confirmacion.component';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  DialogAlertService,
  IMensajeValidacionDto,
} from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-dialog-alert',
  templateUrl: './dialog-alert.component.html',
  styleUrls: ['./dialog-alert.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DialogAlertComponent implements OnInit {
  subscriptionMensaje: Subscription;
  constructor(
    public dialog: MatDialog,
    private dialogAlertService: DialogAlertService
  ) {
    this.subscriptionMensaje = dialogAlertService
      .alerta()
      .subscribe((alert) => this.mostrarAlerta(alert));
  }
  ngOnInit(): void {}
  mostrarAlerta(mensaje: IMensajeValidacionDto) {
    const data: IDataConfirm = {
      titulo: mensaje.titulo,
      descripcion: mensaje.mensaje,
      labelOk: 'Ok',
      ocultarCancelar: true,
    };

    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe((result) => {});
  }
}
