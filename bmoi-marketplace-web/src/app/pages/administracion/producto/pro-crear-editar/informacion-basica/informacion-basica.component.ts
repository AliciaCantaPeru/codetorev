import { OpcioIn } from '@core/models/general.model';
import { AuxProService } from './../pro-crear-editar.service';
import {
  GeneralProductoDto,
  MenuGrupoCategoriaDto,
} from './../../../../../core/models/general.model';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { GeneralService } from '@core/services/general.service';
import { ArbolData } from '@shared/components/c-arbol/c-arbol.component';
import { OpcionEnum } from '@core/soporte/enums';
import { ProductoCategoriaDto } from '@core/models/producto.model';
import { MatCheckboxChange } from '@angular/material/checkbox';
@Component({
  selector: 'app-informacion-basica',
  templateUrl: './informacion-basica.component.html',
  styleUrls: ['./informacion-basica.component.scss'],
})
export class InformacionBasicaComponent implements OnInit {
  @Input() generalListaProducto: GeneralProductoDto;
  listaCategoria: MenuGrupoCategoriaDto[];
  listaCategoriaArbol: ArbolData<number>[] = [];
  listaEstadoProducto: OpcioIn[] = [];
  formGroup: FormGroup;
  constructor(
    public auxProService: AuxProService,
    private generalService: GeneralService,
    private cd: ChangeDetectorRef
  ) {}
  ngOnInit(): void {
    this.listarEstadoProd();
    this.formGroup = this.auxProService.formGroup;
    if (this.formGroup.get('id').value != null) {
      this.listarCategoriaMenuInit(this.formGroup.get('codigoMenu').value);
    }
  }

  listarCategoriaMenuInit(codigo: string) {
    this.generalService.listarCategoria(codigo).subscribe((res) => {
      this.listaCategoria = res.dato;
      this.listarCategoriaArbol();
      this.checkedSelected();
      this.cd.detectChanges();
    });
  }
  listarCategoriaMenu(codigo: string) {
    this.generalService.listarCategoria(codigo).subscribe((res) => {
      this.listaCategoria = res.dato;
      this.listarCategoriaArbol();
      this.cd.detectChanges();
    });
  }
  emitEquiposchangeChecked(event: ArbolData<number>[]) {
    this.auxProService.getFormArray('listaCategorias').clear();
    event.forEach((item) => {
      item.hijos
        .filter((item) => item.checked)
        .forEach((categoria) => {
          const cat: ProductoCategoriaDto = {
            idCategoriaGrupomenu: categoria.data,
          };
          this.auxProService.agregarListCategoriaForm(cat);
        });
    });
    console.log(this.auxProService.getFormArray('listaCategorias'));
  }
  checkedSelected() {
    this.listaCategoriaArbol.forEach((arb) => {
      arb.hijos.forEach((arbHijo) => {
        const find = this.auxProService
          .getFormArray('listaCategorias')
          .controls.find(
            (cont) => cont.get('idCategoriaGrupomenu').value == arbHijo.data
          );
        if (find) {
          arbHijo.checked = true;
        }
      });
      const allChecked = arb.hijos.filter((arb) => arb.checked).length;
      if (allChecked == arb.hijos.length) {
        arb.checked = true;
      }
    });
  }
  listarCategoriaArbol() {
    this.listaCategoriaArbol = [];
    this.listaCategoria.forEach((cat) => {
      const arbol: ArbolData<number> = {
        label: cat.nombre,
        data: cat.id,
        seleccionable: true,
        icono: 'fa fa-folder-o',
        checked: false,
        hijos: this.listarHijos(cat?.listaHijos),
      };
      this.listaCategoriaArbol.push(arbol);
    });
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
        seleccionable: true,
        icono: 'fa fa-folder-o',
        checked: false,
        hijos: subCat.listaHijos ? this.listarHijos(subCat.listaHijos) : [],
      };
      ambientesHijos.push(arbol);
    });
    return ambientesHijos;
  }
  listarEstadoProd() {
    this.listaEstadoProducto = this.generalService.listarDatoOpciones(
      OpcionEnum.ESTADO_PRODUCTO
    );
  }

  changeCheckBox(event: MatCheckboxChange) {
    if (event.checked) {
      const isEmpy =
        this.auxProService.getFormArray('listaPersonalizacion').controls
          .length == 0;
      if (isEmpy) {
        this.auxProService.agregarListaPersonalizacionForm(null);
      }
    }
  }
}
