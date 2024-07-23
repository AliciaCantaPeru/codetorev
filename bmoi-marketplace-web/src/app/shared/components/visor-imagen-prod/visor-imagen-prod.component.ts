import { UtilArchivoService } from '@core/services/util-archivo.service';
import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  Inject,
} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TipoImagen } from './../../../core/soporte/enums';

@Component({
  selector: 'app-visor-imagen-prod',
  templateUrl: './visor-imagen-prod.component.html',
  styleUrls: ['./visor-imagen-prod.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class VisorImagenProdComponent implements OnInit {
  constructor(
    public utilArchivoService: UtilArchivoService,
    public dialogRef: MatDialogRef<VisorImagenProdComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DataVisorDto
  ) {
    console.log(data);
  }

  ngOnInit(): void {}

  guardar() {
    this.dialogRef.close(this.data);
  }

  seleccionarImagen(img: ImagenDto) {
    img.estado = !img.estado;
  }
}
export interface DataVisorDto {
  titulo: string;
  listaImagenes: ImagenDto[];
  tipo: TipoImagen;
}
export interface ImagenDto {
  id: number;
  estado: boolean;
  url: string;
}
