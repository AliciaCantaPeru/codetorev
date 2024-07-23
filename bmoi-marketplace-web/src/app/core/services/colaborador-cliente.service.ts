import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ColaboradorClienteIn,
  ColaboradorClienteMant,
  ColaboradorClienteOut,
} from '@core/models/colaborador-cliente.model';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ColaboradorClienteService {
  private readonly url = environment.url + '/api/colaborador/cliente';

  constructor(private http: HttpClient) {}

  listar(clienteOut: ColaboradorClienteOut) {
    return this.http.post<Respuesta<IPage<ColaboradorClienteIn>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: ColaboradorClienteMant) {
    return this.http.post<Respuesta<ColaboradorClienteMant>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: ColaboradorClienteMant) {
    return this.http.put<Respuesta<ColaboradorClienteMant>>(
      `${this.url}`,
      usuarioMan
    );
  }
  obtener(idPersonaLogeada: number, idPersona: number) {
    return this.http.get<Respuesta<ColaboradorClienteMant>>(
      `${this.url}/${idPersonaLogeada}/${idPersona}`
    );
  }
  actualizarEstado(idPersonaLogeada: number, idPersona: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idPersona}`,
      null
    );
  }
}
