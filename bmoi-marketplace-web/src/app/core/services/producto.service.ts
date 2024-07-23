import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPage } from '@core/models/pageable.model';
import {
  ProductoListaDto,
  ProductoListaFiltroDto,
  ProductoMantDto,
} from '@core/models/producto.model';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private readonly url = environment.url + '/api/producto';
  constructor(private http: HttpClient) {}

  listar(clienteOut: ProductoListaFiltroDto) {
    return this.http.post<Respuesta<IPage<ProductoListaDto>>>(
      `${this.url}/listar`,
      clienteOut
    );
  }
  guardar(usuarioMan: FormData) {
    return this.http.post<Respuesta<ProductoMantDto>>(
      `${this.url}`,
      usuarioMan
    );
  }
  actualizar(usuarioMan: FormData) {
    return this.http.put<Respuesta<ProductoMantDto>>(`${this.url}`, usuarioMan);
  }
  obtener(idPersonaLogeada: number, idProducto: number) {
    return this.http.get<Respuesta<ProductoMantDto>>(
      `${this.url}/${idPersonaLogeada}/${idProducto}`
    );
  }

  eliminar(idPersonaLogeada: number, idProducto: number[]) {
    return this.http.put<Respuesta>(
      `${this.url}/${idPersonaLogeada}`,
      idProducto
    );
  }

  ver(idPersonaLogeada: number, idProducto: number) {
    return this.http.put<Respuesta<ProductoMantDto>>(
      `${this.url}/ver/${idPersonaLogeada}/${idProducto}`,
      null
    );
  }
  destacar(idPersonaLogeada: number, idProducto: number) {
    return this.http.put<Respuesta<ProductoMantDto>>(
      `${this.url}/destacar/${idPersonaLogeada}/${idProducto}`,
      null
    );
  }
}
