import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { ServicioMant, ServicioOut } from '@core/models/servicio.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicioService {
  private readonly url = environment.url + '/api/servicio';
  constructor(private http: HttpClient) {}

  listar(clienteOut: ServicioOut) {
    return this.http.post<Respuesta<IPage<ServicioMant>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: ServicioMant) {
    return this.http.post<Respuesta<ServicioMant>>(`${this.url}`, usuarioMan);
  }
  actualizar(usuarioMan: ServicioMant) {
    return this.http.put<Respuesta<ServicioMant>>(`${this.url}`, usuarioMan);
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<ServicioMant>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  actualizarEstado(idPersonaLogeada: number, idServicio: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idServicio}`,
      null
    );
  }
}
