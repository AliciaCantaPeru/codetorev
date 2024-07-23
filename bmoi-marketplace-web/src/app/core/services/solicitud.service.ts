import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { Respuesta } from '@core/models/respuesta.model';
import {
  ClienteSolicitudOpcionDto,
  DataInicial,
  OpcionDispositivoDto,
  OpcionServicioDto,
  SolicitudInDto,
  SolicitudMantDto,
  SolicitudOutDto,
} from '@core/models/solicitud.model';
import { Opcion } from '@core/soporte/generic.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SolicitudService {
  private readonly url = environment.url + '/api/solicitud';
  constructor(private http: HttpClient) {}
  listar(clienteOut: SolicitudOutDto) {
    return this.http.post<Respuesta<IPage<SolicitudInDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(clienteMant: SolicitudMantDto) {
    return this.http.post<Respuesta<SolicitudMantDto>>(
      `${this.url}`,
      clienteMant
    );
  }
  actualizar(clienteMant: SolicitudMantDto) {
    return this.http.put<Respuesta<SolicitudMantDto>>(
      `${this.url}`,
      clienteMant
    );
  }
  actualizarEstado(idPersonaLogeada: number, idSolicitud: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idSolicitud}`,
      null
    );
  }
  finalizarSolicitud(idPersonaLogeada: number, idSolicitud: number) {
    return this.http.put<Respuesta>(
      `${this.url}/finalizar/${idPersonaLogeada}/${idSolicitud}`,
      null
    );
  }
  obtener(idPersonaLogeada: number, idSolicitud: number) {
    return this.http.get<Respuesta<SolicitudMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idSolicitud}`
    );
  }
  listarClientes() {
    return this.http.get<Respuesta<ClienteSolicitudOpcionDto[]>>(
      `${this.url}/cliente`
    );
  }
  listarPlanesConectividad() {
    return this.http.get<Respuesta<OpcionServicioDto[]>>(`${this.url}/plan`);
  }
  listarServicios() {
    return this.http.get<Respuesta<OpcionServicioDto[]>>(
      `${this.url}/servicio`
    );
  }
  listarDispositivos(idSolicitud: number) {
    return this.http.get<Respuesta<OpcionDispositivoDto[]>>(
      `${this.url}/dispositivo/${idSolicitud}`
    );
  }
  listarUnidadNegocio() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/negocio`);
  }
  listarPersonasNegocio() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/negocio/persona`);
  }
  obtenerTipoCambioActual(moneda: string) {
    return this.http.get<Respuesta<number>>(
      `${this.url}/tipo-cambio/${moneda}`
    );
  }

  listarDataInicial(idSolicitud: number) {
    return this.http.get<Respuesta<DataInicial>>(
      `${this.url}/data-inicial/${idSolicitud}`
    );
  }

  descargarReporte(
    ipPersonaLogeada: number,
    idSolicitud: number,
    tipo: string
  ) {
    return this.http.get(
      `${this.url}/descargarReporte/${ipPersonaLogeada}/${idSolicitud}/${tipo}`,
      {
        responseType: 'blob',
      }
    );
  }
}
