import { Opcion } from '@core/soporte/generic.model';
import { FiltroDateDto } from './util.model';

export interface DashboardDto {
  listaTareas: TareasDto[];
  listaResumenSolicitud: ResumenSolicitudDto[];
  listaProductos: Opcion[];
  listaUnidadesNegocio: Opcion[];
  totalDispositivos: number;
  graficoTotalDispositivos: GraficoTotalDispositivosDto;
}

export interface TareasDto {
  id: number;
  nombre: string;
  cntFecFin: string;
}

export interface ResumenSolicitudDto {
  negociacion: string;
  cantidad: number;
}

export interface DashboarGraficoDto {
  categoria: string[];
  serie: number[];
}

export interface DashboardGraficoOutDto {
  tipoNegociacion: string;
  idUnidadNegocio: number;
  idProducto: number;
  idCliente: number;
  filtro: FiltroDateDto;
}
export interface DashboarGraficoDto {
  categoria: string[];
  serie: number[];
}

export interface GraficoTotalDispositivosDto {
  categories: string[];
  data: number[];
}
