import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { Observable, timer } from 'rxjs';
import { catchError, finalize, switchMap, tap } from 'rxjs/operators';
import { AuthTokenService } from '../services/auth-token.service';
import { Respuesta } from '@core/models/respuesta.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { LoadingService } from '@core/services/loading.service';

@Injectable({
  providedIn: 'root',
})
export class AuthHttpInterceptor implements HttpInterceptor {
  private countRequest = 0;
  private idMessage: string;
  constructor(
    private tokenService: AuthTokenService,
    private loadingService: LoadingService,
    private autenticacionService: AutenticacionService
  ) {}
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<Respuesta<any>>> {
    if (!request.url.includes('/assets')) {
      this.countRequest++;
      if (this.countRequest == 1) {
        this.loadingService.loadingStatus(true);
      }
    }
    const requestClone = this.getRequest(request);
    return next.handle(requestClone).pipe(
      tap((res) => {
        if (res instanceof HttpResponse) {
          if (res.headers.has('Authorization')) {
            const token = res.headers.get('Authorization');
            this.tokenService.setToken(token);
          }
          return res;
        }
      }),
      catchError((err) => {
        if (err instanceof HttpErrorResponse && err.status === 401) {
          this.autenticacionService.logout();
          return this.autenticacionService
            .getRefreshToken()
            .pipe(switchMap((refres) => next.handle(this.addToken(request))));
        }
        throw err;
      }),
      finalize(() => this.finPeticion(request.url))
    );
  }
  finPeticion(url) {
    if (!url.includes('/assets')) {
      this.countRequest--;
      if (this.countRequest === 0) {
        this.loadingService.loadingStatus(false);
      }
    }
  }
  private getRequest(req: HttpRequest<any>) {
    let peticion;
    const conToken = this.conToken(req.url);
    if (conToken) {
      peticion = this.addToken(req);
    } else {
      peticion = req.clone();
    }
    return peticion;
  }
  private addToken(request: HttpRequest<any>) {
    return request.clone({
      setHeaders: {
        Authorization: `${this.tokenService.getToken()}`,
      },
    });
  }
  private conToken(urlWs: string): boolean {
    const listaUrl: string[] = ['/api/autenticacion'];
    for (const url of listaUrl) {
      if (urlWs.includes(url)) {
        return false;
      }
    }
    return true;
  }
}
