import { Component, EventEmitter, OnInit, Output, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ParametroIn } from '@core/models/general.model';
import { GeneralService } from '@core/services/general.service';
import { UtilArchivoService } from '@core/services/util-archivo.service';
import { Parametro } from '@core/soporte/enums';
import { Util } from '@core/soporte/util';

@Component({
  selector: 'app-recurso-url',
  templateUrl: './recurso-url.component.html',
  styleUrls: ['./recurso-url.component.scss'],
})
export class RecursoUrlComponent implements OnInit {
  video = '';
  linkRecurso = '';
  listaTerminos: ParametroIn[] = [];
  formatoVideo: ParametroIn;
  formatoImagen: ParametroIn;
  constructor(
    private generalService: GeneralService,
    public dialogRef: MatDialogRef<RecursoUrlComponent>,
    public utilArchivoService: UtilArchivoService,
    @Inject(MAT_DIALOG_DATA) public data: number
  ) {}

  ngOnInit(): void {
    this.obtenerTerminos();
  }

  obtenerTerminos() {
    this.listaTerminos =
      this.data == 1
        ? this.generalService
            .listarParamatros(Parametro.PRODUCTO_TERMINOS_USO_MEDIA)
            .filter((term) => !term.tipo.includes('YouTube'))
        : this.generalService.listarParamatros(
            Parametro.PRODUCTO_TERMINOS_USO_MEDIA
          );
    this.formatoImagen = this.generalService.listarParamatros(
      Parametro.FORMATO_CARGA_IMAGEN
    )[0];

    this.formatoVideo = this.generalService.listarParamatros(
      Parametro.FORMATO_CARGA_VIDEO
    )[0];
  }

  openLink(param: ParametroIn) {
    window.open(param.valor);
  }

  onChange_linkVideo() {
    if (this.data == 2) {
      if (this.utilArchivoService.isYouTube(this.video)) {
        if (
          !this.video ||
          this.video.trim() === '' ||
          this.video.indexOf('watch') < 0
        ) {
          this.linkRecurso = '';
          return;
        }
        this.linkRecurso = Util.obtenerUrlYoutube(this.video);
      } else {
        this.linkRecurso = this.video.toString();
      }
    } else {
      if (this.utilArchivoService.isImage(this.video)) {
        this.linkRecurso = this.video.toString();
      } else {
        this.linkRecurso = '';
        this.video = '';
      }
    }
  }

  guardar() {
    this.dialogRef.close(this.linkRecurso);
  }
}
