import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import {
  SensorMedidaModeloMantDto,
  SensorMedidaModeloOutDto,
} from '@core/models/sensor-medida-modelo.model';
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SensorMedidaModeloService {
  private readonly url = environment.url + '/api/sensormedidamodelo';
  constructor(private http: HttpClient) {}

  listar(clienteOut: SensorMedidaModeloOutDto) {
    return this.http.post<Respuesta<IPage<SensorMedidaModeloMantDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  listarSensorMedida() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/sensormedida`);
  }
  guardar(usuarioMan: SensorMedidaModeloMantDto) {
    return this.http.post<Respuesta<SensorMedidaModeloMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: SensorMedidaModeloMantDto) {
    return this.http.put<Respuesta<SensorMedidaModeloMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<SensorMedidaModeloMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  delete(idPersonaLogeada: number, id: number) {
    return this.http.delete<Respuesta>(`${this.url}/${idPersonaLogeada}/${id}`);
  }
}
