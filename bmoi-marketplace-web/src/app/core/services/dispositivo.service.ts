import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  DispositivoIn,
  DispositivoMant,
  DispositivoOut,
  Producto,
  Proveedor,
  UdsMedida,
} from '@core/models/dispositivo.model';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DispositivoService {
  private readonly url = environment.url + '/api/dispositivo';
  constructor(private http: HttpClient) {}

  listar(clienteOut: DispositivoOut) {
    return this.http.post<Respuesta<IPage<DispositivoIn>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: DispositivoMant) {
    return this.http.post<Respuesta<DispositivoMant>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: DispositivoMant) {
    return this.http.put<Respuesta<DispositivoMant>>(`${this.url}`, usuarioMan);
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<DispositivoMant>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  actualizarEstado(idPersonaLogeada: number, idProducto: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idProducto}`,
      null
    );
  }
  listarUdsMedida() {
    return this.http.get<Respuesta<UdsMedida[]>>(`${this.url}/udsmedida`);
  }
  listarProducto() {
    return this.http.get<Respuesta<Producto[]>>(`${this.url}/producto`);
  }
  listarProveedor() {
    return this.http.get<Respuesta<Proveedor[]>>(`${this.url}/proveedor`);
  }
  listarSensor() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/sensor`);
  }
  listarModelo(idSensor: number) {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/modelo/${idSensor}`);
  }
}
