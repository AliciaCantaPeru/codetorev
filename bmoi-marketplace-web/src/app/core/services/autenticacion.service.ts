import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  AutLoginOut,
  AutUsuarioIn,
  CambiarContraseniaDto,
  KeyOutDto,
  TokenDecode,
  TokenDto,
} from '@core/models/autenticacion.model';
import { Respuesta } from '@core/models/respuesta.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthTokenService } from './auth-token.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { ROL } from '@core/soporte/rol';

@Injectable({
  providedIn: 'root',
})
export class AutenticacionService {
  private readonly urlAutenticacion = environment.url + '/api/autenticacion';
  private readonly rol = ROL;
  constructor(
    private http: HttpClient,
    private router: Router,
    private authTokenService: AuthTokenService,
    private jwtHelper: JwtHelperService
  ) {}

  login(autLoginOut: AutLoginOut) {
    return this.http.post<Respuesta<AutUsuarioIn>>(
      `${this.urlAutenticacion}/login`,
      autLoginOut
    );
  }
  generarLinkRecuperarContrasenia(tipoUsuario: string, correo: string) {
    return this.http.get<Respuesta>(
      `${this.urlAutenticacion}/cambiarContrasenia/enviarLink/${tipoUsuario}/${correo}`
    );
  }
  verificarKey(key: KeyOutDto) {
    return this.http.post<Respuesta<string>>(
      `${this.urlAutenticacion}/cambiarContrasenia/verificarKey`,
      key
    );
  }
  cambiarContrasenia(cambiarContrasenia: CambiarContraseniaDto) {
    return this.http.post<Respuesta>(
      `${this.urlAutenticacion}/cambiarContrasenia`,
      cambiarContrasenia
    );
  }
  activarCuenta(cambiarContrasenia: CambiarContraseniaDto) {
    return this.http.post<Respuesta>(
      `${this.urlAutenticacion}/activarCuenta`,
      cambiarContrasenia
    );
  }
  guardarTema(idUsuarioSeller: number, tema: string) {
    return this.http.post<Respuesta<any>>(
      `${this.urlAutenticacion}/tema/${idUsuarioSeller}/${tema}`,
      null
    );
  }
  getCurrentUser(): AutUsuarioIn {
    const decodedToken = this.jwtHelper.decodeToken<TokenDecode>(
      this.authTokenService.getToken()
    );
    return decodedToken?.user;
  }
  permitirModificar() {
    const idRol = this.getCurrentUser().idRol;
    return this.rol.ROL_ADMIN === idRol || idRol === this.rol.ROL_MASTER
      ? true
      : false;
  }
  getRefreshToken() {
    return this.http
      .post<Respuesta<TokenDto>>(
        `${this.urlAutenticacion}/refresh`,
        this.getTokenOut()
      )
      .pipe(
        map((resp) => {
          this.authTokenService.setToken(resp.dato.token);
          return true;
        })
      );
  }
  logout() {
    this.salir();
  }
  private salir() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['/']);
  }
  private getTokenOut() {
    const token: string = this.authTokenService.getToken();
    const tokenOut: TokenDto = { token };
    return tokenOut;
  }
}
