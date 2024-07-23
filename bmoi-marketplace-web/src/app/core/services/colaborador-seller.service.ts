import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ColaboradorSellerIn,
  ColaboradorSellerMant,
  ColaboradorSellerOut,
} from '@core/models/colaborador-seller.model';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ColaboradorSellerService {
  private readonly url = environment.url + '/api/colaborador/seller';

  constructor(private http: HttpClient) {}

  listar(clienteOut: ColaboradorSellerOut) {
    return this.http.post<Respuesta<IPage<ColaboradorSellerIn>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: FormData) {
    return this.http.post<Respuesta<ColaboradorSellerMant>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: FormData) {
    return this.http.put<Respuesta<ColaboradorSellerMant>>(
      `${this.url}`,
      usuarioMan
    );
  }
  obtener(idPersonaLogeada: number, idPersona: number) {
    return this.http.get<Respuesta<ColaboradorSellerMant>>(
      `${this.url}/${idPersonaLogeada}/${idPersona}`
    );
  }
  actualizarEstado(idPersonaLogeada: number, idsPersonas: number[]) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}`,
      idsPersonas
    );
  }
}
