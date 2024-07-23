import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-confirmacion',
  templateUrl: './dialog-confirmacion.component.html',
  styleUrls: ['./dialog-confirmacion.component.scss'],
})
export class DialogConfirmacionComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<DialogConfirmacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IDataConfirm
  ) {}

  ngOnInit(): void {}
}

export interface IDataConfirm {
  titulo: string;
  descripcion: string;
  labelOk: string;
  ocultarCancelar?: boolean;
}
