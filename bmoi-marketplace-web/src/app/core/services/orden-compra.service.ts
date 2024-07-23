import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  OpcionSolicitudesIn,
  OrdenCompraInDto,
  OrdenCompraMantDto,
  OrdenCompraOutDto,
} from '@core/models/orden-compra.model';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrdenCompraService {
  private readonly url = environment.url + '/api/ordenCompra';
  constructor(private http: HttpClient) {}
  listar(clienteOut: OrdenCompraOutDto) {
    return this.http.post<Respuesta<IPage<OrdenCompraInDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }

  guardar(clienteMant: OrdenCompraMantDto) {
    return this.http.post<Respuesta<OrdenCompraMantDto>>(
      `${this.url}`,
      clienteMant
    );
  }
  listarSolicitudes() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/solicitudes`);
  }
  finalizarSolicitud(idPersonaLogeada: number, idSolicitud: number) {
    return this.http.put<Respuesta>(
      `${this.url}/finalizar/${idPersonaLogeada}/${idSolicitud}`,
      null
    );
  }
}
