import { Util } from '@core/soporte/util';
import { Router } from '@angular/router';
import { PathApp } from './../../../../../layout/container-page/app-menu/app.menu';
import { SelectionModel } from '@angular/cdk/collections';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {
  ColaboradorSellerIn,
  ColaboradorSellerOut,
} from '@core/models/colaborador-seller.model';
import { IColumnMat } from '@core/models/util.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { ColaboradorSellerService } from '@core/services/colaborador-seller.service';
import { Alert } from '@core/soporte/alert';
import {
  DialogConfirmacionComponent,
  IDataConfirm,
} from '@shared/components/dialog-confirmacion/dialog-confirmacion.component';
import { TableOptionsComponent } from '@shared/components/table-options/table-options.component';
import { merge, Observable, of } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { ColaboradorBmoiService } from '@core/services/colaborador-bmoi.service';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-col-bmo-listar',
  templateUrl: './col-bmo-listar.component.html',
  styleUrls: ['./col-bmo-listar.component.scss'],
})
export class ColBmoListarComponent implements OnInit, AfterViewInit {
  selectColumns: IColumnMat[] = [
    { column: 'select', label: 'select', checked: true },
    { column: 'priNombre', label: 'Primer nombre', checked: true },
    { column: 'segNombre', label: 'Segundo nombre', checked: true },
    { column: 'priApellido', label: 'Primer Apellido', checked: true },
    { column: 'segApellido', label: 'Segundo Apellido', checked: true },
    { column: 'email', label: 'Email', checked: true },
    { column: 'celular1', label: 'Celular', checked: true },
    { column: 'celular2', label: 'Celular', checked: false },
    { column: 'cargo', label: 'Cargo', checked: true },
    { column: 'sexo', label: 'Género', checked: false },
    { column: 'tipDocumento', label: 'Tip. Doc.', checked: false },
    { column: 'numDocumento', label: 'Num. Doc.', checked: false },
    { column: 'fecNacimiento', label: 'Fec. Nacimiento', checked: false },
    { column: 'usuRegistro', label: 'Usuario Registro', checked: false },
    { column: 'fecRegistro', label: 'Fec. Registro', checked: false },
    { column: 'usuActualizacion', label: 'Usuario Modifico', checked: false },
    { column: 'fecActualizacion', label: 'Fec. Modificación', checked: false },
    { column: 'acciones', label: 'Acciones', checked: true },
  ];

  displayedColumns$: Observable<string[]>;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(TableOptionsComponent) optionsComponent: TableOptionsComponent;
  dataSource = new MatTableDataSource<ColaboradorSellerIn>();
  selection = new SelectionModel<ColaboradorSellerIn>(true, []);
  totalElementos = 0;
  filtrando = false;

  constructor(
    public dialog: MatDialog,
    private colaborador: ColaboradorBmoiService,
    public dialogAlertService: DialogAlertService,
    private autenticacionService: AutenticacionService,
    private router: Router
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
          const filtroUsuario: ColaboradorSellerOut = {
            texto,
            cantidad: 20,
            idSeller: this.autenticacionService?.getCurrentUser()?.idSeller,
            pagina: this.paginator.pageIndex,
            sortCampo: this.sort.active,
            sortOrden: this.sort.direction,
            idPersonaLogeada:
              this.autenticacionService?.getCurrentUser()?.idSellerPersona,
          };
          return this.colaborador.listar(filtroUsuario);
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
  checkboxLabel(row?: ColaboradorSellerIn): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${
      row.id
    }`;
  }
  abrirCrearEditarDialogo(data: ColaboradorSellerIn) {
    const id = data ? data.id : -1;
    this.router.navigate([PathApp.routeColBmoiReg, id]);
  }

  confirmarEliminar(item: ColaboradorSellerIn) {
    const idEliminar =
      item != null ? [item.id] : this.selection.selected.map((sell) => sell.id);
    const data: IDataConfirm = {
      titulo: 'Eliminar colaborador',
      descripcion: Util.getMensaje(idEliminar.length),
      labelOk: 'Eliminar',
    };

    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });
    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.colaborador
          .actualizarEstado(
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
    const filtroUsuario: ColaboradorSellerOut = {
      texto: null,
      cantidad: 20,
      idSeller: this.autenticacionService?.getCurrentUser()?.idSeller,
      pagina: this.paginator.pageIndex,
      sortCampo: this.sort.active,
      sortOrden: this.sort.direction,
      idPersonaLogeada:
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
    };
    this.colaborador
      .listar(filtroUsuario)
      .subscribe((res) => (this.dataSource.data = res.dato.content));
  }
  resetPagina(): void {
    this.paginator.pageIndex = 0;
  }
}
