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
export class SellerService {
  private readonly urlCliente = environment.url + '/api/seller';

  constructor(private http: HttpClient) {}

  listar(clienteOut: EmpresaOut) {
    return this.http.post<Respuesta<IPage<EmpresaIn>>>(
      `${this.urlCliente}/listar`,
      clienteOut
    );
  }

  guardar(clienteMant: FormData) {
    return this.http.post<Respuesta<EmpresaMantDto>>(
      `${this.urlCliente}`,
      clienteMant
    );
  }

  actualizar(clienteMant: FormData) {
    return this.http.put<Respuesta<EmpresaMantDto>>(
      `${this.urlCliente}`,
      clienteMant
    );
  }

  eliminar(idPersonaLogeada: number, idCliente: number[]) {
    return this.http.put<Respuesta>(
      `${this.urlCliente}/${idPersonaLogeada}`,
      idCliente
    );
  }

  obtener(idPersonaLogeada: number, idCliente: number) {
    return this.http.get<Respuesta<EmpresaMantDto>>(
      `${this.urlCliente}/${idPersonaLogeada}/${idCliente}`
    );
  }
  resetearCuenta(idPersonaLogeada: number, idSeller: number) {
    return this.http.put<Respuesta<EmpresaMantDto>>(
      `${this.urlCliente}/resetear-cuenta/${idPersonaLogeada}/${idSeller}`,
      null
    );
  }
}
