import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { Observable } from 'rxjs';
import { AuthTokenService } from '../services/auth-token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthLoginGuard implements CanActivate {
  constructor(
    private autService: AutenticacionService,
    private router: Router,
    private authTokenService: AuthTokenService,
    private jwtHelper: JwtHelperService
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    const user = this.autService.getCurrentUser();
    const token = this.authTokenService.getToken();
    if (user && token && !this.jwtHelper.isTokenExpired(token)) {
      this.router.navigate([PathApp.routeDashboard]);
      return false;
    }
    return true;
  }
}
