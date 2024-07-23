import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import { TipoCambioMant, TipoCambioOut } from '@core/models/tipo-cambio.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TipoCambioService {
  private readonly url = environment.url + '/api/tipocambio';

  constructor(private http: HttpClient) {}

  listar(clienteOut: TipoCambioOut) {
    return this.http.post<Respuesta<IPage<TipoCambioMant>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: TipoCambioMant) {
    return this.http.post<Respuesta<TipoCambioMant>>(`${this.url}`, usuarioMan);
  }
  actualizar(usuarioMan: TipoCambioMant) {
    return this.http.put<Respuesta<TipoCambioMant>>(`${this.url}`, usuarioMan);
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<TipoCambioMant>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
}
