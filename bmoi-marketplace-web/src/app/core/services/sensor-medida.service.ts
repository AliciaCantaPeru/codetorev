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
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SensorMedidaService {
  private readonly url = environment.url + '/api/sensormedida';
  constructor(private http: HttpClient) {}
  listar(clienteOut: SensorMedidaOutDto) {
    return this.http.post<Respuesta<IPage<SensorMedidaMantDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  listarUnidadMedida() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/udsMedida`);
  }
  guardar(usuarioMan: FormData) {
    return this.http.post<Respuesta<SensorMedidaMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: FormData) {
    return this.http.put<Respuesta<SensorMedidaMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<SensorMedidaMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  delete(idPersonaLogeada: number, id: number) {
    return this.http.delete<Respuesta>(`${this.url}/${idPersonaLogeada}/${id}`);
  }
}
