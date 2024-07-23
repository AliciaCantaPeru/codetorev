import { distinctUntilChanged } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DialogAlertService {
  private subject = new Subject<IMensajeValidacionDto>();
  constructor() {}

  public mostrarError(mensaje: string) {
    const men: IMensajeValidacionDto = {
      titulo: 'Error',
      mensaje,
    };
    this.enviarMensaje(men);
  }
  public mostrarInfo(mensaje: string) {
    const men: IMensajeValidacionDto = {
      titulo: 'Aviso',
      mensaje,
    };
    this.enviarMensaje(men);
  }
  public mostrarWarning(mensaje: string) {
    const men: IMensajeValidacionDto = {
      titulo: 'Advertencia',
      mensaje,
    };
    this.enviarMensaje(men);
  }
  public mostrarConfirmacion(mensaje: string) {
    const men: IMensajeValidacionDto = {
      titulo: 'Operación completada',
      mensaje,
    };
    this.enviarMensaje(men);
  }
  public updateSuccess() {
    this.mostrarConfirmacion('El registro se actualizó con éxito');
  }
  public errorHttp(error) {
    const messageError = error?.error?.mensaje
      ? error?.error?.mensaje
      : 'Lo sentimos no se pudo realizar la operación';
    this.mostrarWarning(messageError);
  }
  public saveSuccess() {
    this.mostrarConfirmacion('El registro se guardo con éxito');
  }
  public deleteSuccess() {
    this.mostrarConfirmacion('El registro fue eliminado con éxito');
  }
  private enviarMensaje(respuestaServidor: IMensajeValidacionDto) {
    this.subject.next(respuestaServidor);
  }
  public alerta(): Observable<IMensajeValidacionDto> {
    return this.subject.asObservable().pipe(distinctUntilChanged());
  }
}
export interface IMensajeValidacionDto {
  titulo: string;
  mensaje: string;
}
