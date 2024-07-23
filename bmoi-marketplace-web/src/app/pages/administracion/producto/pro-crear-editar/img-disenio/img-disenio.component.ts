import {
  ProductoImagenDto,
  ProductoImagenPropioDto,
} from './../../../../../core/models/producto.model';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup } from '@angular/forms';
import { MenuGrupoCategoriaDto } from './../../../../../core/models/general.model';
import { Component, OnInit, Input, ChangeDetectorRef } from '@angular/core';
import { GeneralProductoDto } from '@core/models/general.model';
import { GeneralService } from '@core/services/general.service';
import { ArbolData } from '@shared/components/c-arbol/c-arbol.component';
import { AuxProService } from '../pro-crear-editar.service';
import {
  DataVisorDto,
  ImagenDto,
  VisorImagenProdComponent,
} from '@shared/components/visor-imagen-prod/visor-imagen-prod.component';
import { TipoImagen } from '@core/soporte/enums';

@Component({
  selector: 'app-img-disenio',
  templateUrl: './img-disenio.component.html',
  styleUrls: ['./img-disenio.component.scss'],
})
export class ImgDisenioComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  listaCategoriaArbol: ArbolData<number>[] = [];
  listaCategoriaPropiaArbol: ArbolData<number>[] = [];
  formGroup: FormGroup;
  constructor(
    public auxProService: AuxProService,
    private generalService: GeneralService,
    public dialog: MatDialog,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
    this.listarCategoriaDisenio(-1);
    this.listarCategoriaDisenioPropio();
  }

  listarCategoriaDisenioPropio() {
    this.listaCategoriaPropiaArbol = this.listarCategoriaArbol(
      this.auxProService.getFormArray('listaGrupoCategoria').value
    );
    this.cd.detectChanges();
  }
  listarCategoriaDisenio(codigo: number) {
    this.generalService.listarCategoriaDisenio(codigo).subscribe((res) => {
      this.listaCategoriaArbol = this.listarCategoriaArbol(res.dato);
      this.cd.detectChanges();
    });
  }
  listarCategoriaArbol(listaCategoria: MenuGrupoCategoriaDto[]) {
    const listaCategoriaArbol = [];
    listaCategoria?.forEach((cat) => {
      const arbol: ArbolData<number> = {
        label: cat.nombre,
        data: cat.id,
        seleccionable: false,
        icono: 'fa fa-folder-o',
        checked: false,
        hijos: this.listarHijos(cat?.listaHijos),
      };
      listaCategoriaArbol.push(arbol);
    });
    return listaCategoriaArbol;
  }
  private listarHijos(ambientes: MenuGrupoCategoriaDto[]): ArbolData<number>[] {
    if (!ambientes) {
      return [];
    }
    const ambientesHijos: ArbolData<number>[] = [];
    ambientes.forEach((subCat) => {
      const arbol: ArbolData<number> = {
        label: subCat.nombre,
        data: subCat.id,
        seleccionable: subCat.listaHijos ? false : true,
        icono: 'fa fa-folder-o',
        checked: false,
        hijos: subCat.listaHijos ? this.listarHijos(subCat.listaHijos) : [],
      };
      ambientesHijos.push(arbol);
    });
    return ambientesHijos;
  }
  emitImgChangeChecked(event: ArbolData<number>) {
    const data: DataVisorDto = this.getDataImgPredeteminado(event);
    this.abrirVisorImagen(data);
  }
  private getDataImgPredeteminado(event: ArbolData<number>) {
    const listaImagenes: ImagenDto[] = [];
    this.generalListaProducto.listaImagen
      .filter((img) => img.idSubcategoria == event.data)
      .forEach((img) => {
        const findControl = this.auxProService
          .getFormArray('listaImg')
          .controls.find((cont) => {
            return cont.get('idDigImgSubImg').value == img.idDigImgSubImg;
          });
        const imgVisor: ImagenDto = {
          id: img.idDigImgSubImg,
          url: img.url,
          estado: findControl ? true : false,
        };
        listaImagenes.push(imgVisor);
      });
    const data: DataVisorDto = {
      titulo: 'Imágenes predeterminadas',
      listaImagenes,
      tipo: TipoImagen.PREDETERMINADO,
    };
    return data;
  }

  emitImgPropioChangeChecked(event) {
    const data: DataVisorDto = this.getDataImgPropio(event);
    this.abrirVisorImagen(data);
  }

  private getDataImgPropio(event: ArbolData<number>) {
    const listaImagenes: ImagenDto[] = [];
    const a = this.auxProService
      .getFormArray('listaImgPropio')
      .controls.filter((cont) => cont.get('idDigImgSubImg').value == event.data)
      .forEach((img) => {
        const imgVisor: ImagenDto = {
          url: img.get('url').value,
          estado: img.get('estado').value,
          id: img.get('id').value,
        };
        listaImagenes.push(imgVisor);
      });
    const data: DataVisorDto = {
      titulo: 'Imágenes propias',
      listaImagenes,
      tipo: TipoImagen.PROPIO,
    };
    return data;
  }
  abrirVisorImagen(data: DataVisorDto) {
    const dialogRef = this.dialog.open(VisorImagenProdComponent, {
      width: '100%',
      height: '100%',
      maxWidth: '90vw',
      maxHeight: '90vh',
      panelClass: 'mat-dialog-override',
      data,
    });
    dialogRef.afterClosed().subscribe((result: DataVisorDto) => {
      console.log(result);
      if (result != null) {
        if (result.tipo == TipoImagen.PROPIO) {
          this.auxProService
            .getFormArray('listaImgPropio')
            .controls.forEach((control) => {
              const idControl = control.get('id').value;
              const findImg = result.listaImagenes.find(
                (img) => img.id == idControl
              );
              control.get('estado').setValue(findImg ? findImg.estado : false);
            });
        }

        if (result.tipo == TipoImagen.PREDETERMINADO) {
          this.auxProService.getFormArray('listaImg').clear();
          result.listaImagenes
            .filter((img) => img.estado)
            .forEach((img) => {
              const imgDo: ProductoImagenDto = {
                idDigImgSubImg: img.id,
              };
              this.auxProService.agregarListImgForm(imgDo);
            });
        }
      }
    });
  }
}
