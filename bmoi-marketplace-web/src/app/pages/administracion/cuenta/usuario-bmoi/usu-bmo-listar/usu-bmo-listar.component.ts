import { Util } from '@core/soporte/util';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { Router } from '@angular/router';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { SelectionModel } from '@angular/cdk/collections';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UsuarioIn, UsuarioOut } from '@core/models/usuario.model';
import { IColumnMat } from '@core/models/util.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { UsuarioSellerService } from '@core/services/usuario-seller.service';
import { Alert } from '@core/soporte/alert';
import {
  DialogConfirmacionComponent,
  IDataConfirm,
} from '@shared/components/dialog-confirmacion/dialog-confirmacion.component';
import { TableOptionsComponent } from '@shared/components/table-options/table-options.component';
import { merge, Observable, of } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { UsuarioBmoiService } from '@core/services/usuario-bmoi.service';

@Component({
  selector: 'app-usu-bmo-listar',
  templateUrl: './usu-bmo-listar.component.html',
  styleUrls: ['./usu-bmo-listar.component.scss'],
})
export class UsuBmoListarComponent implements OnInit, AfterViewInit {
  selectColumns: IColumnMat[] = [
    // { column: 'id', label: 'Id', checked: true },
    // { column: 'cliente', label: 'Seller', checked: true },
    { column: 'priNombre', label: 'Nombres', checked: true },
    { column: 'segNombre', label: 'Segundo Nombre', checked: true },
    { column: 'priApellido', label: 'Primer Apellido', checked: true },
    { column: 'segApellido', label: 'Segundo Apellido', checked: false },
    { column: 'email', label: 'Email', checked: true },
    { column: 'rol', label: 'Rol', checked: true },
    { column: 'fecNacimiento', label: 'Fec. Nacimiento', checked: false },
    { column: 'genero', label: 'Género', checked: false },
    { column: 'tipDocumento', label: 'Tip. Doc.', checked: false },
    { column: 'numDocumento', label: 'Num. Doc.', checked: false },
    { column: 'celular', label: 'Celular', checked: false },
    { column: 'telFijo', label: 'Télefono', checked: false },
    { column: 'cargo', label: 'Cargo', checked: true },
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
  dataSource = new MatTableDataSource<UsuarioIn>();
  selection = new SelectionModel<UsuarioIn>(true, []);
  totalElementos = 0;
  filtrando = false;

  constructor(
    public dialog: MatDialog,
    private usuarioService: UsuarioBmoiService,
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
          const filtroUsuario: UsuarioOut = {
            texto,
            cantidad: 20,
            pagina: this.paginator.pageIndex,
            sortCampo: this.sort.active,
            sortOrden: this.sort.direction,
            idPersonaLogeada:
              this.autenticacionService?.getCurrentUser()?.idSellerPersona,
          };
          return this.usuarioService!.listar(filtroUsuario);
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

  abrirCrearEditarDialogo(data: UsuarioIn) {
    const id = data ? data.id : -1;
    this.router.navigate([PathApp.routeCuentaBmoiRegistro, id]);
  }

  confirmarEliminar(item: UsuarioIn) {
    const idEliminar =
      item != null ? [item.id] : this.selection.selected.map((sell) => sell.id);
    const data: IDataConfirm = {
      titulo: 'Eliminar usuario',
      descripcion: Util.getMensaje(idEliminar.length),
      labelOk: 'Eliminar',
    };

    const dialogRef = this.dialog.open(DialogConfirmacionComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.usuarioService
          .eliminar(
            this.autenticacionService?.getCurrentUser()?.idSellerPersona,
            idEliminar
          )
          .subscribe(
            (res) => {
              this.listar();
              this.dialogAlertService.deleteSuccess();
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
    const filtroUsuario: UsuarioOut = {
      texto: null,
      cantidad: 20,
      pagina: this.paginator.pageIndex,
      sortCampo: this.sort.active,
      sortOrden: this.sort.direction,
      idPersonaLogeada:
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
    };
    this.usuarioService!.listar(filtroUsuario).subscribe(
      (res) => (this.dataSource.data = res.dato.content)
    );
  }
  resetPagina(): void {
    this.paginator.pageIndex = 0;
  }
}
