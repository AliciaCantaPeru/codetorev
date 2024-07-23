import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthTokenService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private readonly RECORDAR_CUENTA = 'RECORDAR_CUENTA';
  constructor() {}
  public setToken(token: string) {
    const recordarCuenta = this.getRecordarCuenta();
    if (recordarCuenta) {
      localStorage.setItem(this.JWT_TOKEN, token);
    } else {
      sessionStorage.setItem(this.JWT_TOKEN, token);
    }
  }

  public getToken() {
    const recordarCuenta = this.getRecordarCuenta();
    if (recordarCuenta) {
      return localStorage.getItem(this.JWT_TOKEN);
    } else {
      return sessionStorage.getItem(this.JWT_TOKEN);
    }
  }

  public removeToken() {
    const recordarCuenta = this.getRecordarCuenta();
    if (recordarCuenta) {
      localStorage.removeItem(this.JWT_TOKEN);
    } else {
      sessionStorage.removeItem(this.JWT_TOKEN);
    }
  }
  public getRecordarCuenta() {
    const recordarCuenta = localStorage.getItem(this.RECORDAR_CUENTA);
    if (recordarCuenta == null) {
      return false;
    }
    return recordarCuenta === '1' ? true : false;
  }
  public changeRecordarCuenta(recordar: boolean) {
    const recordarCuenta = recordar ? '1' : '0';
    localStorage.setItem(this.RECORDAR_CUENTA, recordarCuenta);
  }
}
