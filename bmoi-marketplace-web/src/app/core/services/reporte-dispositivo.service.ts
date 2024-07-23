import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ConsumoDispositivoDto,
  DispositivoReporteDto,
  ReporteGraficoDto,
  ReporteDispositivoOutDto,
  ConsumoHistoricoInDto,
  ConsumoHistoricoOutDto,
  LocationDto,
  GenerarPdfConsumoOutDto,
} from '@core/models/reporte-dispositivo.model';
import { Respuesta } from '@core/models/respuesta.model';
import { Opcion } from '@core/soporte/generic.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ReporteDispositivoService {
  private readonly url = environment.url + '/api/reporte/dispositivo';

  constructor(private http: HttpClient) {}

  listarCliente() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.url}/cliente`);
  }
  listarNegociacion(idCliente: number) {
    return this.http
      .get<Respuesta<string[]>>(`${this.url}/negociacion/${idCliente}`)
      .pipe(
        map((res) => {
          const dato = res.dato.map((neg) => {
            const opcion: Opcion = {
              id: neg,
              nombre: neg,
            };
            return opcion;
          });
          return dato;
        })
      );
  }
  listarProducto(idCliente: number, negociacion: string) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.url}/producto/${idCliente}/${negociacion}`
    );
  }
  listarDispositivos(out: ReporteDispositivoOutDto) {
    return this.http.post<Respuesta<DispositivoReporteDto[]>>(
      `${this.url}`,
      out
    );
  }
  obtenerDataAcumulada(consumoHistoricoOutDto: ConsumoHistoricoOutDto) {
    return this.http.post<Respuesta<ConsumoDispositivoDto>>(
      `${this.url}/data`,
      consumoHistoricoOutDto
    );
  }
  obtenerGraficoSvg(device: string, idCliente: number) {
    return this.http.get<Respuesta<ReporteGraficoDto>>(
      `${this.url}/grafico/${device}/${idCliente}`
    );
  }
  obtenerConsumoHistorico(filtro: ConsumoHistoricoOutDto) {
    return this.http.post<Respuesta<ConsumoHistoricoInDto>>(
      `${this.url}/consumo`,
      filtro
    );
  }
  obtenerBateriaHistorico(filtro: ConsumoHistoricoOutDto) {
    return this.http.post<Respuesta<ConsumoHistoricoInDto>>(
      `${this.url}/bateria`,
      filtro
    );
  }
  listarLocation(filtro: ConsumoHistoricoOutDto) {
    return this.http.post<Respuesta<LocationDto[]>>(
      `${this.url}/location`,
      filtro
    );
  }

  obtenerFileReporteConsumo(data: GenerarPdfConsumoOutDto) {
    return this.http.post(`${this.url}/descargar`, data, {
      responseType: 'blob',
    });
  }
}
