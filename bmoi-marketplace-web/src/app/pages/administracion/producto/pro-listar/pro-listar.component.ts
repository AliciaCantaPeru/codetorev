import { Util } from '@core/soporte/util';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { Router } from '@angular/router';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { SelectionModel } from '@angular/cdk/collections';
import { merge, Observable, of } from 'rxjs';
import {
  IDataConfirm,
  DialogConfirmacionComponent,
} from '@shared/components/dialog-confirmacion/dialog-confirmacion.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { IColumnMat } from '@core/models/util.model';
import { TableOptionsComponent } from '@shared/components/table-options/table-options.component';
import { ProductoService } from '@core/services/producto.service';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { Alert } from '@core/soporte/alert';
import {
  ProductoListaDto,
  ProductoListaFiltroDto,
} from '@core/models/producto.model';

@Component({
  selector: 'app-pro-listar',
  templateUrl: './pro-listar.component.html',
  styleUrls: ['./pro-listar.component.scss'],
})
export class ProListarComponent implements OnInit, AfterViewInit {
  selectColumns: IColumnMat[] = [
    { column: 'select', label: 'select', checked: true },
    { column: 'multimedia1', label: 'Imagen', checked: true },
    { column: 'multimedia2', label: 'Imagen 2', checked: false },
    { column: 'sku', label: 'Sku', checked: true },
    { column: 'stock', label: 'Stock', checked: true },
    { column: 'nombrecorto', label: 'Nombre del producto', checked: true },
    { column: 'preciobasico', label: 'Precio', checked: true },
    { column: 'onlinestatus', label: 'Online', checked: true },
    { column: 'destacado', label: 'Destacado', checked: true },
    { column: 'avgstar', label: 'Start', checked: false },
    { column: 'descripcion', label: 'Descripción', checked: false },
    { column: 'enviogratis', label: 'Envio gratis', checked: false },
    { column: 'estado', label: 'Estado', checked: false },
    { column: 'f2', label: 'f2', checked: false },
    { column: 'f7', label: 'f7', checked: false },
    { column: 'f8', label: 'f8', checked: false },
    { column: 'f9', label: 'f9', checked: false },
    // { column: 'skupadre', label: 'Sku', checked: false },
    { column: 'titulo', label: 'Tirulo', checked: false },
    { column: 'acciones', label: 'Acciones', checked: true },
  ];
  displayedColumns$: Observable<string[]>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(TableOptionsComponent) optionsComponent: TableOptionsComponent;
  dataSource = new MatTableDataSource<ProductoListaDto>();
  selection = new SelectionModel<ProductoListaDto>(true, []);
  totalElementos = 0;
  filtrando = false;
  seccion = 0;
  tipoVista = 1;
  constructor(
    public dialog: MatDialog,
    public dialogAlertService: DialogAlertService,
    private router: Router,
    private productoService: ProductoService,
    public autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por página';
    this.paginator._intl.getRangeLabel = (
      page: number,
      pageSize: number,
      length: number
    ) => {
      const start = page * pageSize + 1;
      const end = (page + 1) * pageSize;
      return `${start} - ${end}`;
    };
  }
  ngAfterViewInit() {
    merge(
      this.sort.sortChange,
      this.paginator.page,
      this.optionsComponent.filter
    )
      .pipe(
        startWith({}),
        switchMap(() => {
          this.filtrando = true;
          const texto =
            this.optionsComponent?.filterValue.trim() === ''
              ? null
              : this.optionsComponent.filterValue;
          const filtroUsuario = this.getDataFilter(texto);
          return this.productoService.listar(filtroUsuario);
        }),
        map((data) => {
          this.filtrando = false;
          this.totalElementos = data.dato.totalElements;
          return data.dato.content;
        }),
        catchError((error) => {
          this.filtrando = false;
          return of([]);
        })
      )
      .subscribe((res) => (this.dataSource.data = res));
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }
  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
    //console.log(this.selection);
  }
  checkboxLabel(row?: ProductoListaDto): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${
      row.id
    }`;
  }
  abrirCrearEditarDialogo(data: ProductoListaDto) {
    const id = data ? data.id : -1;
    this.router.navigate([PathApp.routeProductoRegistro, id]);
  }

  confirmarEliminar(item: ProductoListaDto) {
    const idEliminar =
      item != null ? [item.id] : this.selection.selected.map((sell) => sell.id);
    const data: IDataConfirm = {
      titulo: 'Eliminar producto',
      descripcion: Util.getMensaje(idEliminar.length),
      labelOk: 'Eliminar',
    };
    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.productoService
          .eliminar(
            this.autenticacionService?.getCurrentUser()?.idSellerPersona,
            idEliminar
          )
          .subscribe(
            (res) => {
              this.listar();
              this.dialogAlertService.deleteSuccess();
              this.selection.clear();
            },
            (error) => this.dialogAlertService.errorHttp(error)
          );
      }
    });
  }

  displayColumns($columns: Observable<string[]>) {
    this.displayedColumns$ = $columns;
  }

  filtrar(event: string) {
    this.dataSource.filter = event.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  listar() {
    this.optionsComponent.filterValue = '';
    const filtroUsuario = this.getDataFilter(null);
    this.productoService
      .listar(filtroUsuario)
      .subscribe((res) => (this.dataSource.data = res.dato.content));
  }
  resetPagina(): void {
    this.paginator.pageIndex = 0;
  }
  selectedIndexChange(event: number) {
    console.log(event);
    this.seccion = event;
    this.listar();
  }
  getDataFilter(texto: string) {
    const filtroUsuario: ProductoListaFiltroDto = {
      texto,
      cantidad: 20,
      pagina: this.paginator.pageIndex,
      sortCampo: this.sort?.active ?? 'nombrecorto',
      sortOrden: this.sort?.direction ?? 'asc',
      seccion: this.seccion,
      idPersonaLogeada:
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
    };
    return filtroUsuario;
  }
  destacar(data: ProductoListaDto) {
    this.productoService
      .destacar(
        this.autenticacionService.getCurrentUser().idSellerPersona,
        data.id
      )
      .subscribe((res) => this.listar());
  }
  ver(data: ProductoListaDto) {
    this.productoService
      .ver(this.autenticacionService.getCurrentUser().idSellerPersona, data.id)
      .subscribe((res) => this.listar());
  }
  changeView(event: number) {
    this.tipoVista = event;
  }
}
