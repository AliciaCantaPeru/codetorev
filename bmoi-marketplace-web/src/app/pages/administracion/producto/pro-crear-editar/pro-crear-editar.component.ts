import { DialogAlertService } from '@core/services/dialog-alert.service';
import { AutenticacionService } from './../../../../core/services/autenticacion.service';
import { Alert } from '@core/soporte/alert';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { GeneralService } from '@core/services/general.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TABS } from './pro-data';
import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  ViewChildren,
  QueryList,
  HostListener,
  ChangeDetectorRef,
  NgZone,
} from '@angular/core';
import { PerfectScrollbarComponent } from 'ngx-perfect-scrollbar';
import {
  GeneralProductoDto,
  MenuGrupoCategoriaDto,
} from '@core/models/general.model';
import { AuxProService } from './pro-crear-editar.service';
import { ProductoService } from '@core/services/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-pro-crear-editar',
  templateUrl: './pro-crear-editar.component.html',
  styleUrls: ['./pro-crear-editar.component.scss'],
})
export class ProCrearEditarComponent implements OnInit {
  listaSecciones = TABS;
  @ViewChild('perfectScroll') perfectScroll: PerfectScrollbarComponent;
  idSeleccionado = 1;
  generalListaProducto: GeneralProductoDto;
  listaCategoria: MenuGrupoCategoriaDto[];
  id: number;
  constructor(
    private generalService: GeneralService,
    public auxProService: AuxProService,
    public dialogAlertService: DialogAlertService,
    private cd: ChangeDetectorRef,
    private productoService: ProductoService,
    private route: ActivatedRoute,
    private router: Router,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((param) => (this.id = Number(param.id)));
    this.listarGeneralProducto();
    this.obtener();
  }

  forceNavigate(id: number) {
    let targetElement = document.getElementById(id + '');
    targetElement.scrollIntoView({ behavior: 'smooth' });
    // this.perfectScroll.directiveRef.update();
    // this.perfectScroll.directiveRef.scrollToElement(targetElement, 0, 800);
  }

  onScroll() {
    for (const { id, nombre } of this.listaSecciones) {
      let targetElement = document.getElementById(id + '');
      // const visibleTotal = this.esVisibleCompleto(targetElement);
      const visibleParcial = this.esVisibleParcial(targetElement);
      if (visibleParcial) {
        //console.log('idSeleccionado', this.idSeleccionado);
        // this.ngZone.run(() => {
        //   this.idSeleccionado = id;
        // });
        break;
      }
    }
  }

  private esVisibleParcial(elemento: HTMLElement) {
    const caja = elemento.getBoundingClientRect();
    var alturaViewport =
      window.innerHeight || document.documentElement.clientHeight;
    var cajaDentroV = caja.top >= 90 && caja.top <= alturaViewport * 0.3;
    return cajaDentroV;
  }
  listarGeneralProducto() {
    this.generalService
      .listarGeneralProducto()
      .subscribe((res) => (this.generalListaProducto = res.dato));
  }
  guardar() {
    const formControl = this.auxProService.obtenerFormGroupGuardar();
    if (formControl.invalid) {
      this.auxProService.formGroup.markAllAsTouched();
      this.cd.detectChanges();
      return;
    }
    const data = formControl.value;
    if (this.id == -1) {
      this.productoService.guardar(data).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeProducto]);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    } else {
      this.productoService.actualizar(data).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeProducto]);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    }
  }
  obtener() {
    if (this.id !== -1) {
      this.productoService
        .obtener(
          this.autenticacionService.getCurrentUser().idSellerPersona,
          this.id
        )
        .subscribe((res) => {
          this.auxProService.setFormGroup(res.dato);
        });
    } else {
      this.auxProService.setFormGroup(null);
    }
  }

  cancelar() {
    this.router.navigate([PathApp.routeProducto]);
  }
}
