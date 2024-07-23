import { SolicitudMantDto } from './solicitud.model';

export interface OrdenCompraOutDto {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}
export interface OrdenCompraInDto {
  id: number;
  cliente: string;
  cntFecFin: string;
  cntFecInicio: string;
  comentarios: string;
  contrato: string;
  fecActualizacion: string;
  fecGeneracionOc: string;
  fecRegistro: string;
  frcFacturacion: string;
  total: number;
  intFrcfacturacion: number;
  moneda: string;
  valorCambio: string;
  negociacion: string;
  nombre: string;
  trmCompra: string;
  usuActualizacion: string;
  usuRegistro: string;
  vendedor: string;
}

export interface OrdenCompraMantDto {
  idPersonaLogeada: number;
  idSolicitud: number;
  solicitud: SolicitudMantDto;
}

export interface OpcionSolicitudesIn {
  id: number;
  nombre: string;
  frcFacturacion: string;
}
