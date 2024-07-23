import {
  GeneralDto,
  GeneralProductoDto,
  MenuGrupoCategoriaDto,
} from '../models/general.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  OpcioIn,
  ParametroIn,
  ValorCambioIn,
} from '@core/models/general.model';
import { Respuesta } from '@core/models/respuesta.model';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { Opcion } from '@core/soporte/generic.model';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import {
  ContenidoWebDto,
  ContenidWebDto,
} from '@core/models/content-web.model';

@Injectable({
  providedIn: 'root',
})
export class ContenidoWebService {
  private readonly urlGeneral = environment.url + '/api/contenido-web';
  constructor(private http: HttpClient) {}

  listarContenidoWeb() {
    return this.http.get<Respuesta<ContenidWebDto>>(`${this.urlGeneral}`);
  }
}
