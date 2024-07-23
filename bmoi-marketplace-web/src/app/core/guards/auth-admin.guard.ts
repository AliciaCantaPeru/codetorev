import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { TokenDecode } from '@core/models/autenticacion.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { AuthTokenService } from '../services/auth-token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthAdminGuard implements CanActivate {
  constructor(
    private autService: AutenticacionService,
    private authTokenService: AuthTokenService,
    private jwtHelper: JwtHelperService
  ) {}
  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const token = this.authTokenService.getToken();
    const decodedToken = this.jwtHelper.decodeToken<TokenDecode>(token);
    if (token && !this.jwtHelper.isTokenExpired(token) && decodedToken?.user) {
      return true;
    }

    if (this.jwtHelper.isTokenExpired(token)) {
      const refreshTokenCorrecto = await this.getRefreshToken();
      if (!refreshTokenCorrecto) {
        this.autService.logout();
      }
      return refreshTokenCorrecto;
    }
    this.autService.logout();
    return true;
  }

  private async getRefreshToken(): Promise<boolean> {
    try {
      return await this.autService.getRefreshToken().toPromise();
    } catch (error) {
      this.autService.logout();
      return false;
    }
  }
}
