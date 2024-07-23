import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  EmpresaIn,
  EmpresaMantDto,
  EmpresaOut,
} from '@core/models/seller.model';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class NegocioService {
  private readonly url = environment.url + '/api/negocio';

  constructor(private http: HttpClient) {}

  listar(clienteOut: EmpresaOut) {
    return this.http.post<Respuesta<IPage<EmpresaIn>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }

  guardar(clienteMant: FormData) {
    return this.http.post<Respuesta<EmpresaMantDto>>(
      `${this.url}`,
      clienteMant
    );
  }

  actualizar(clienteMant: FormData) {
    return this.http.put<Respuesta<EmpresaMantDto>>(`${this.url}`, clienteMant);
  }

  actualizarEstado(idPersonaLogeada: number, idCliente: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idCliente}`,
      null
    );
  }

  obtener(idPersonaLogeada: number, idCliente: number) {
    return this.http.get<Respuesta<EmpresaMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idCliente}`
    );
  }
}
