import { MatDialog } from '@angular/material/dialog';
import { AuxProService } from './../../pro-crear-editar.service';
import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedSocial } from '@core/models/seller.model';
import {
  GeneralProductoDto,
  MenuGrupoCategoriaDto,
  OpcioIn,
  OptionSelectDto,
  ParametroIn,
} from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { NzUploadFile, NzUploadXHRArgs } from 'ng-zorro-antd/upload';
import { ArchivoService } from '@core/services/archivo.service';
import { ProductoImagenPropioDto } from '@core/models/producto.model';
import { RecursoUrlComponent } from '@shared/components/recurso-url/recurso-url.component';
import { UtilArchivoService } from '@core/services/util-archivo.service';
@Component({
  selector: 'app-img-custom',
  templateUrl: './img-custom.component.html',
  styleUrls: ['./img-custom.component.scss'],
})
export class ImgCustomComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  listaGrupoCategoria: MenuGrupoCategoriaDto[] = [];
  formGroup: FormGroup;
  listaRedesSociales: RedSocial[] = [];
  listaRedes: OpcioIn[] = [];
  permitirModificar = false;
  listaGrupos: {
    indice: number;
    listaCategoria: MenuGrupoCategoriaDto[];
    listaSubcategoria: MenuGrupoCategoriaDto[];
  }[] = [];
  constructor(
    private fb: FormBuilder,
    private generalService: GeneralService,
    public auxProService: AuxProService,
    private archivoService: ArchivoService,
    private cd: ChangeDetectorRef,
    public dialog: MatDialog,
    public utilArchivoService: UtilArchivoService
  ) {}
  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
    this.listaRedes = this.generalService.listarDatoOpciones(
      OpcionEnum.REDES_SOCIALES
    );
    this.listarCategoriaDisenio();
  }

  openDialog() {
    const dialogRef = this.dialog.open(RecursoUrlComponent, { data: 1 });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const multimedia: ProductoImagenPropioDto = {
          url: result,
          estado: true,
          id: null,
        };
        this.auxProService.agregarListImgPropioForm(multimedia);
        this.cd.detectChanges();
      }
    });
  }

  remove(index: number) {
    this.auxProService.removeAtImgPropioForm(index);
  }

  customUploadReq = (item: NzUploadXHRArgs) => {
    try {
      this.guardarFile(item);
    } catch (error) {
      console.log(error);
    }
  };
  guardarFile(file: any) {
    const formData = new FormData();
    formData.append(
      'idSeller',
      '1'
      // this.authService.getCurrentUser().idSeller.toString()
    );
    formData.append('files', file);
    return this.archivoService.guardarArchivo(formData).subscribe((res) => {
      res.dato.forEach((img) => {
        const multimedia: ProductoImagenPropioDto = {
          url: img.url,
          estado: true,
          id: null,
        };
        this.auxProService.agregarListImgPropioForm(multimedia);
        this.cd.detectChanges();
      });
    });
  }
  listarCategoriaDisenio() {
    this.generalService.listarCategoriaDisenio(-1).subscribe((res) => {
      this.listaGrupoCategoria = res.dato;
      this.cd.detectChanges();
    });
  }

  listarCategoria(idGrupo: number, indice: number) {
    let row = this.getRow(indice);
    let categoria = this.listaGrupoCategoria.find((gru) => gru.id == idGrupo);
    if (row) {
      row.listaCategoria = categoria ? categoria.listaHijos : [];
      row.listaSubcategoria = [];
    } else {
      row = {
        indice,
        listaCategoria: categoria ? categoria.listaHijos : [],
        listaSubcategoria: [],
      };
      this.listaGrupos.push(row);
    }
    const cntrolImg =
      this.auxProService.getFormArray('listaImgPropio').controls[indice];
    console.log(cntrolImg);
    cntrolImg.get('idCategoria').setValue(null);
    cntrolImg.get('idDigImgSubImg').setValue(null);
  }
  listarSubcategoria(categoria: number, indice: number) {
    const cntrolImg =
      this.auxProService.getFormArray('listaImgPropio').controls[indice];
    const idGrupo = cntrolImg.get('idGrupo').value;
    let categoriaFind = this.listaGrupoCategoria.find(
      (gru) => gru.id == idGrupo
    );
    let categoriaSelect = categoriaFind.listaHijos.find(
      (cat) => cat.id == categoria
    );
    let row = this.getRow(indice);
    if (row) {
      row.listaSubcategoria = categoriaSelect ? categoriaSelect.listaHijos : [];
    } else {
      row = {
        indice,
        listaCategoria: categoriaSelect ? categoriaSelect.listaHijos : [],
        listaSubcategoria: [],
      };
      this.listaGrupos.push(row);
    }
    cntrolImg.get('idDigImgSubImg').setValue(null);
  }

  getRow(indice: number) {
    return this.listaGrupos?.find((grup) => grup.indice == indice);
  }
  getListaCategoria(indice) {
    return this.listaGrupos?.find((grup) => grup.indice == indice)
      ?.listaCategoria;
  }
  getListaSubcategoria(indice) {
    return this.listaGrupos?.find((grup) => grup.indice == indice)
      ?.listaSubcategoria;
  }
}
