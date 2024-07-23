import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import { PlanMantDto, PlanOut } from '@core/models/plan.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PlanService {
  private readonly url = environment.url + '/api/plan';

  constructor(private http: HttpClient) {}

  listar(clienteOut: PlanOut) {
    return this.http.post<Respuesta<IPage<PlanMantDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: PlanMantDto) {
    return this.http.post<Respuesta<PlanMantDto>>(`${this.url}`, usuarioMan);
  }
  actualizar(usuarioMan: PlanMantDto) {
    return this.http.put<Respuesta<PlanMantDto>>(`${this.url}`, usuarioMan);
  }
  obtener(idPersonaLogeada: number, idUsuario: number) {
    return this.http.get<Respuesta<PlanMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idUsuario}`
    );
  }
  actualizarEstado(idPersonaLogeada: number, idPlan: number) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}/${idPlan}`,
      null
    );
  }
}
