import { ArchivoDto } from '../models/archivo.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Respuesta } from '@core/models/respuesta.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ArchivoService {
  private readonly urlArchivo = environment.url + '/api/archivo';
  constructor(private http: HttpClient) {}

  guardarArchivo(fromData: FormData) {
    return this.http.post<Respuesta<ArchivoDto[]>>(
      `${this.urlArchivo}`,
      fromData
    );
  }
}
