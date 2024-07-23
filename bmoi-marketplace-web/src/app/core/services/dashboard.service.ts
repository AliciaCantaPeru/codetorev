import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  DashboardDto,
  DashboardGraficoOutDto,
  DashboarGraficoDto,
} from '@core/models/dashboard.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private readonly url = environment.url + '/api/dashboard';

  constructor(private http: HttpClient) {}

  obtenerDato(idSeller: number) {
    return this.http.get<Respuesta<string>>(
      `${this.url}/dia-restante/${idSeller}`
    );
  }
}
