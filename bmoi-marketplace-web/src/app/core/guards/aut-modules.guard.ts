import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { Observable } from 'rxjs';
import { ROL_REPORTES } from '@core/soporte/rol';

@Injectable({
  providedIn: 'root',
})
export class AutModulesGuard implements CanActivate {
  constructor(
    private autenticacionService: AutenticacionService,
    private router: Router
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.verificarRol(route);
  }
  private verificarRol(route: ActivatedRouteSnapshot): boolean {
    const roles: number[] = route.data['roles'];
    let estaAutorizado = false;
    const user = this.autenticacionService?.getCurrentUser();
    if (roles) {
      for (const rol of roles) {
        if (user.idRol === rol) {
          estaAutorizado = true;
          break;
        }
      }
    }
    if (!estaAutorizado) {
      if (user.idRol === ROL_REPORTES || user.idRol === ROL_REPORTES) {
        if (!this.router.url.includes('reporte')) {
          this.router.navigate([PathApp.routeReporteDispositivo]);
        }
      }
    }
    return estaAutorizado;
  }
}
