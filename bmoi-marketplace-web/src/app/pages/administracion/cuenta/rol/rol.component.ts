import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { RolMantDto } from '@core/models/rol.model';
import { IColumnMat } from '@core/models/util.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { RolService } from '@core/services/rol.service';
import { SocketService } from '@core/services/socket.service';
import { Observable } from 'rxjs';
import { RolCrearEditarComponent } from './rol-crear-editar/rol-crear-editar.component';

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrls: ['./rol.component.scss'],
  providers: [SocketService],
})
export class RolComponent implements OnInit {
  selectColumns: IColumnMat[] = [
    // { column: 'id', label: 'Id', checked: true },
    { column: 'nombre', label: 'Nombre', checked: true },
    { column: 'descripcion', label: 'Descripción', checked: true },
    { column: 'estado', label: 'Estado', checked: true },
    { column: 'acciones', label: 'Acciones', checked: true },
  ];
  displayedColumns$: Observable<string[]>;
  dataSource: MatTableDataSource<RolMantDto> = new MatTableDataSource();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(
    public dialog: MatDialog,
    private rolService: RolService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.listarRoles();
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

  abrirCrearEditarDialogo(data: RolMantDto) {
    const dialogRef = this.dialog.open(RolCrearEditarComponent, { data });
    dialogRef.afterClosed().subscribe((result: RolMantDto) => {
      if (result && result != null) {
        this.listarRoles();
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

  listarRoles() {
    const idPersonaLogeada =
      this.autenticacionService?.getCurrentUser()?.idSellerPersona;
    this.rolService
      .listar(idPersonaLogeada)
      .subscribe((res) => (this.dataSource.data = res.dato));
  }
}
