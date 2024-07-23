import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { IMenu } from '@core/models/menu.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { IconRegistryService } from '@core/services/icon-registry.service';
import { TipoUsuario } from '@core/soporte/enums';
import { filter } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PathApp } from './app.menu';

@Component({
  selector: 'app-app-menu',
  templateUrl: './app-menu.component.html',
  styleUrls: ['./app-menu.component.scss'],
})
export class AppMenuComponent implements OnInit {
  public menu: IMenu[] = [];
  version = environment.version;
  @Output() changeTitle: EventEmitter<string> = new EventEmitter();

  constructor(
    public router: Router,
    public autenticacionService: AutenticacionService
  ) {
    const { tipoUsuario } = autenticacionService.getCurrentUser();
    if (tipoUsuario == TipoUsuario.BMOI) {
      this.menu = PathApp.appBmoi;
    }
    if (tipoUsuario == TipoUsuario.SELLER) {
      this.menu = PathApp.appSeller;
    }
    this.menu.forEach((men) =>
      men.children.forEach(
        (hij) =>
          (hij.ocultar =
            hij.title == 'Agregar'
              ? !autenticacionService.permitirModificar()
              : hij.ocultar)
      )
    );
  }

  ngOnInit(): void {
    this.changeTitle.emit(this.getNameMenu(this.menu));
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((rus) => this.changeTitle.emit(this.getNameMenu(this.menu)));
  }
  getNameMenu(menu: IMenu[]): string {
    let title = '';
    for (const item of menu) {
      if (title != '') {
        break;
      }
      if (item.children && item.children.length > 0) {
        title = this.getNameMenu(item.children);
      } else {
        if (this.router.url.includes(item.route)) {
          title = item.title;
        }
      }
    }
    this.router.navigated = false;
    window.scrollTo(0, 0);
    return title;
  }

  navegar(item: IMenu) {
    if (item.children) {
      PathApp.appBmoi
        .filter((pat) => pat.title != item.title)
        .forEach((path) => (path.close = false));
      PathApp.appSeller
        .filter((pat) => pat.title != item.title)
        .forEach((path) => (path.close = false));
      item.close = !item.close;
    } else {
      this.router.navigate([item.route]);
    }
  }
}
