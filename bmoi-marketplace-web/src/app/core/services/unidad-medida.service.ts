import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import {
  SensorMedidaModeloMantDto,
  SensorMedidaModeloOutDto,
} from '@core/models/sensor-medida-modelo.model';
import {
  SensorMedidaMantDto,
  SensorMedidaOutDto,
} from '@core/models/sensor-medida.model';
import {
  UnidadMedidaMantDto,
  UnidadMedidaOutDto,
} from '@core/models/uds-medida.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UnidadMedidaService {
  private readonly url = environment.url + '/api/unidadmedida';
  constructor(private http: HttpClient) {}

  listar(clienteOut: UnidadMedidaOutDto) {
    return this.http.post<Respuesta<IPage<UnidadMedidaMantDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: UnidadMedidaMantDto) {
    return this.http.post<Respuesta<UnidadMedidaMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: UnidadMedidaMantDto) {
    return this.http.put<Respuesta<UnidadMedidaMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<UnidadMedidaMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  delete(idPersonaLogeada: number, id: number) {
    return this.http.delete<Respuesta>(`${this.url}/${idPersonaLogeada}/${id}`);
  }
}
