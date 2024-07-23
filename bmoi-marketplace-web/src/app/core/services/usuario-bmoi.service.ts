import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import {
  OpcionPersona,
  UsuarioIn,
  UsuarioMantDto,
  UsuarioOut,
} from '@core/models/usuario.model';
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsuarioBmoiService {
  private readonly urlUsuario = environment.url + '/api/usuario/bmoi';

  constructor(private http: HttpClient) {}

  listar(clienteOut: UsuarioOut) {
    return this.http.post<Respuesta<IPage<UsuarioIn>>>(
      `${this.urlUsuario}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: UsuarioMantDto) {
    return this.http.post<Respuesta<UsuarioMantDto>>(
      `${this.urlUsuario}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: UsuarioMantDto) {
    return this.http.put<Respuesta<UsuarioMantDto>>(
      `${this.urlUsuario}`,
      usuarioMan
    );
  }
  eliminar(idPersonaLogeada: number, idUsuario: number[]) {
    return this.http.put<Respuesta>(
      `${this.urlUsuario}/${idPersonaLogeada}`,
      idUsuario
    );
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<UsuarioMantDto>>(
      `${this.urlUsuario}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  listarClientesOptions(idPersonaLogeada: number, idCliente: number) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.urlUsuario}/opcion/${idPersonaLogeada}/${idCliente}`
    );
  }
  listarPersonasOptions(
    idPersonaLogeada: number,
    idCliente: number,
    idPersona: number
  ) {
    return this.http.get<Respuesta<OpcionPersona[]>>(
      `${this.urlUsuario}/personas/${idPersonaLogeada}/${idCliente}/${idPersona}`
    );
  }
  listarRolesOptions(idPersonaLogeada: number) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.urlUsuario}/personas/roles/${idPersonaLogeada}`
    );
  }
}
