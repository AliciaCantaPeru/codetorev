import { RecursoUrlComponent } from './../../../../../shared/components/recurso-url/recurso-url.component';
import { AuxProService } from './../pro-crear-editar.service';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { MatDialog } from '@angular/material/dialog';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NzUploadXHRArgs } from 'ng-zorro-antd/upload';
import { ArchivoService } from '@core/services/archivo.service';
import { ListaMultimediaMantDto } from '@core/models/producto.model';

@Component({
  selector: 'app-img-video',
  templateUrl: './img-video.component.html',
  styleUrls: ['./img-video.component.scss'],
})
export class ImgVideoComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    public auxProService: AuxProService,
    private authService: AutenticacionService,
    private archivoService: ArchivoService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  openDialog() {
    const dialogRef = this.dialog.open(RecursoUrlComponent, { data: 2 });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const multimedia: ListaMultimediaMantDto = {
          grdMultimedia: result,
        };
        this.auxProService.agregarListaMultimediaForm(multimedia);
        this.cd.detectChanges();
      }
    });
  }
  guardarFile(file: any) {
    const formData = new FormData();
    formData.append(
      'idSeller',
      '1'
      // this.authService.getCurrentUser().idSeller.toString()
    );
    formData.append('files', file);
    this.archivoService.guardarArchivo(formData).subscribe((res) => {
      res.dato.forEach((img) => {
        const multimedia: ListaMultimediaMantDto = {
          grdMultimedia: img.url,
        };
        this.auxProService.agregarListaMultimediaForm(multimedia);
        this.cd.detectChanges();
      });
    });
  }
  emitLink(event) {
    console.log(event);
  }
  customUploadReq = (item: NzUploadXHRArgs) => {
    return this.guardarFile(item);
  };
}
