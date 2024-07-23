import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Respuesta } from '@core/models/respuesta.model';
import { RolMantDto } from '@core/models/rol.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RolService {
  private readonly urlCliente = environment.url + '/api/rol';

  constructor(private http: HttpClient) {}

  listar(idPersonaLogeada: number) {
    return this.http.get<Respuesta<RolMantDto[]>>(
      `${this.urlCliente}/${idPersonaLogeada}`
    );
  }

  actualizar(idPersonaLogeada: number, rolMant: RolMantDto) {
    return this.http.put<Respuesta>(
      `${this.urlCliente}/${idPersonaLogeada}`,
      rolMant
    );
  }
}
