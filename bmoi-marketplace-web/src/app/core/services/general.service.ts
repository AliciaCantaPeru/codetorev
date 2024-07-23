import {
  GeneralDto,
  GeneralProductoDto,
  MenuGrupoCategoriaDto,
} from './../models/general.model';
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

@Injectable({
  providedIn: 'root',
})
export class GeneralService {
  private readonly urlGeneral = environment.url + '/api/general';

  private listaParametros: ParametroIn[];
  private listaOpciones: OpcioIn[];

  constructor(private http: HttpClient) {}

  async obtener() {
    return await this.http
      .get<Respuesta<GeneralDto>>(`${this.urlGeneral}/configuracion`)
      .pipe(
        map((res) => {
          this.listaParametros = res.dato.listaParametro;
          this.listaOpciones = res.dato.listaOpciones;
        })
      )
      .toPromise();
  }

  listarParamatros(parametro: Parametro) {
    return this.listaParametros.filter((par) => par.clave === parametro);
  }
  listarDatoOpciones(parametro: OpcionEnum) {
    return this.listaOpciones.filter((par) => par.fieldname === parametro);
  }

  listarPaises() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.urlGeneral}/paises`);
  }
  listarDepartamentos(idPais: number) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.urlGeneral}/departamentos/${idPais}`
    );
  }
  listarProvincias(idDepartamento: number) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.urlGeneral}/provincias/${idDepartamento}`
    );
  }
  listarDistritos(idProvincia: number) {
    return this.http.get<Respuesta<Opcion[]>>(
      `${this.urlGeneral}/distritos/${idProvincia}`
    );
  }
  listarValorCambio() {
    return this.http.get<Respuesta<ValorCambioIn[]>>(
      `${this.urlGeneral}/valor-cambio`
    );
  }
  correoUnico(correo: string, idPersona: number, tipoUsuario: string) {
    return this.http.get<Respuesta<boolean>>(
      `${this.urlGeneral}/correo-unico/${correo}/${idPersona}/${tipoUsuario}`
    );
  }
  listarGeneralProducto() {
    return this.http.get<Respuesta<GeneralProductoDto>>(
      `${this.urlGeneral}/general-producto`
    );
  }
  listarCategoria(codigo: string) {
    return this.http.get<Respuesta<MenuGrupoCategoriaDto[]>>(
      `${this.urlGeneral}/menu-grupo-categoria-opcion/${codigo}`
    );
  }
  listarCategoriaDisenio(idGrupo: number) {
    return this.http.get<Respuesta<MenuGrupoCategoriaDto[]>>(
      `${this.urlGeneral}/categoria-disenio/${idGrupo}`
    );
  }
  listarPostulante() {
    return this.http.get<Respuesta<Opcion[]>>(`${this.urlGeneral}/postulante`);
  }
}
