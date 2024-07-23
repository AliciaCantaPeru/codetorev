import { DialogAlertService } from '@core/services/dialog-alert.service';
import { SelectionModel } from '@angular/cdk/collections';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSelectChange } from '@angular/material/select';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ValorCambioIn } from '@core/models/general.model';
import { EmpresaIn, EmpresaOut } from '@core/models/seller.model';
import { IColumnMat } from '@core/models/util.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { SellerService } from '@core/services/seller.service';
import { GeneralService } from '@core/services/general.service';
import { Alert } from '@core/soporte/alert';
import { Util } from '@core/soporte/util';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import {
  DialogConfirmacionComponent,
  IDataConfirm,
} from '@shared/components/dialog-confirmacion/dialog-confirmacion.component';
import { TableOptionsComponent } from '@shared/components/table-options/table-options.component';
import { merge, Observable, of } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
@Component({
  selector: 'app-cli-lista',
  templateUrl: './cli-lista.component.html',
  styleUrls: ['./cli-lista.component.scss'],
})
export class CliListaComponent implements OnInit, AfterViewInit {
  selectColumns: IColumnMat[] = [
    { column: 'select', label: 'select', checked: true },
    { column: 'nomComercial', label: 'Nom. comercial', checked: true },
    { column: 'contacto', label: 'Contacto', checked: true },
    { column: 'ruc', label: 'Ruc', checked: true },
    { column: 'rubro', label: 'Rubro', checked: true },
    { column: 'totPedMonto', label: 'Ventas acumuladas', checked: true },
    { column: 'totPedidos', label: 'Cantidad pedidos', checked: true },
    { column: 'fecFinal', label: 'Fecha Fin Plan', checked: true },
    { column: 'telefono', label: 'Telefono', checked: false },
    { column: 'celular', label: 'Celular', checked: false },
    { column: 'email1', label: 'Email1', checked: false },
    { column: 'email2', label: 'Email2', checked: false },
    { column: 'tipEmpresa', label: 'Tipo empresa', checked: false },
    { column: 'website', label: 'Web', checked: false },
    { column: 'otros', label: 'Otros', checked: false },
    { column: 'descripcion', label: 'Descripción', checked: false },
    { column: 'feciniActividades', label: 'Fec. Inicio', checked: false },
    { column: 'usuRegistro', label: 'Usu. registro', checked: false },
    { column: 'fecRegistro', label: 'Fec.  registro', checked: false },
    { column: 'usuActualizacion', label: 'Usu. modificación', checked: false },
    { column: 'fecActualizacion', label: 'Fec.  modificación', checked: false },
    { column: 'acciones', label: 'Acciones', checked: true },
  ];
  displayedColumns$: Observable<string[]>;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(TableOptionsComponent) optionsComponent: TableOptionsComponent;
  dataSource = new MatTableDataSource<EmpresaIn>();
  selection = new SelectionModel<EmpresaIn>(true, []);
  totalElementos = 0;
  filtrando = false;
  idRolLogeado: number = null;
  listaValorCambio: ValorCambioIn[];
  valoCambioOld: string;
  constructor(
    public dialog: MatDialog,
    private router: Router,
    private sellerService: SellerService,
    public dialogAlertService: DialogAlertService,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.listarValorCambio();
    this.idRolLogeado = this.autenticacionService?.getCurrentUser()?.idRol;
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
          const filtroCliente: EmpresaOut = {
            texto,
            cantidad: 20,
            pagina: this.paginator.pageIndex,
            sortCampo: this.sort.active,
            sortOrden: this.sort.direction,
            idPersonaLogeada:
              this.autenticacionService.getCurrentUser().idSellerPersona,
          };
          return this.sellerService.listar(filtroCliente);
        }),
        map((data) => {
          this.filtrando = false;
          this.totalElementos = data.dato.totalElements;
          return data.dato.content;
        }),
        catchError((error) => {
          //console.log(error);
          this.filtrando = false;
          return of([]);
        })
      )
      .subscribe((res) => {
        this.dataSource.data = res;
        //console.log('this.dataSource.data', this.dataSource.data);
      });
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
  checkboxLabel(row?: EmpresaIn): string {
    //console.log(this.selection);

    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${
      row.id
    }`;
  }
  resetPagina(): void {
    this.paginator.pageIndex = 0;
  }

  abrirCrearEditarDialogo(data: EmpresaIn) {
    const id = data ? data.id : -1;
    this.router.navigate([PathApp.routeSellerRegistro, id]);
  }

  confirmarEliminar(item: EmpresaIn) {
    const idEliminar =
      item != null ? [item.id] : this.selection.selected.map((sell) => sell.id);
    const data: IDataConfirm = {
      titulo: 'Eliminar Seller',
      descripcion: Util.getMensaje(idEliminar.length),
      labelOk: 'Eliminar',
    };
    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.sellerService
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

  listar() {
    this.optionsComponent.filterValue = '';
    const filtroUsuario: EmpresaOut = {
      texto: null,
      cantidad: 20,
      pagina: this.paginator.pageIndex,
      sortCampo: this.sort.active,
      sortOrden: this.sort.direction,
      idPersonaLogeada:
        this.autenticacionService.getCurrentUser().idSellerPersona,
    };
    this.sellerService!.listar(filtroUsuario).subscribe(
      (res) => (this.dataSource.data = res.dato.content)
    );
  }

  listarValorCambio() {
    this.generalService.listarValorCambio().subscribe((res) => {
      this.listaValorCambio = res.dato;
    });
  }
  changeTipoMoneda(select: MatSelectChange, element: EmpresaIn) {
    const monedaOld = this.listaValorCambio.find(
      (mon) => mon.simbolo == this.valoCambioOld
    );
    const monedaNew = this.listaValorCambio.find(
      (mon) => mon.simbolo == select.value
    );
    const factorTipoCambio =
      Number(monedaOld.valCompra) / Number(monedaNew.valCompra);
    element.totPedMonto = Util.getDecimal(
      element.totPedMonto * factorTipoCambio
    );
    this.valoCambioOld = null;
  }
  onMatSelectOpen(element: EmpresaIn) {
    this.valoCambioOld = element.monedaCambio;
  }
}
