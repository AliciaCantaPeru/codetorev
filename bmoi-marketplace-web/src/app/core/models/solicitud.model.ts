import { Opcion } from '@core/soporte/generic.model';

export interface OpcionDispositivoDto {
  id: number;
  nombre: string;
  prcVenta: number;
  stock: number;
  listaDispositivos: Opcion[];
}

export interface OpcionServicioDto {
  id: number;
  nombre: string;
  costo: number;
}

export interface SolicitudOutDto {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}
export interface SolicitudInDto {
  id: number;
  cliente: string;
  cntFecFin: string;
  cntFecInicio: string;
  comentarios: string;
  contrato: string;
  fecActualizacion: string;
  fecRegistro: string;
  frcFacturacion: string;
  intFrcfacturacion: number;
  moneda: string;
  valorCambio: string;
  negociacion: string;
  nombre: string;
  estSolicitud: string;
  trmCompra: string;
  usuActualizacion: string;
  usuRegistro: string;
  vendedor: string;
}
export interface DetalleDto {
  id: number;
  idProducto: number;
  nombreProducto?: string;
  cantidad: number;
  cstUnitario: number;
  cstTotal: number;
  idPlanConectividad: number;
  nombrePlan?: string;
  cstPlan: number;
  cstPlanUnitario: number;
  cstServicios: number;
  subtotal: number;
  otrCostos: number;
  total: number;
}

export interface DetalleServicioDto {
  id: number;
  idServicio?: number;
  nombre?: string;
  cstUnitario?: number;
  cstTotal?: number;
}

export interface OtrosCostoDto {
  id?: number;
  descripcion?: string;
  signo?: string;
  simbolo?: string;
  tipo?: string;
  costo?: number;
  valor?: number;
}

export interface SolicitudDto {
  id?: number;
  nombre?: string;
  cntFecFin?: string;
  cntFecInicio?: string;
  comentarios?: string;
  contrato?: string;
  frcFacturacion?: string;
  intFrcfacturacion?: number;
  idCliente?: number;
  nombreCliente?: string;
  idPersonaNegocio?: number;
  idUnidadNegocio?: number;
  nombreUnidadNegocio?: string;
  moneda?: string;
  valorCambio?: number;
  simbolo?: string;
  negociacion?: string;
  trmCompra?: string;
  logoUnidadNegocio?: string;
  rucUnidadNegocio?: string;
  clientePersonaPrincipal?: string;
}

export interface SolicitudMantDto {
  detalle: DetalleDto;
  detalleServicio: DetalleServicioDto[];
  idPersonaLogeada: number;
  otrosCostos: OtrosCostoDto[];
  solicitud: SolicitudDto;
  idDispositivos: Opcion[];
}
export interface UndNegocioOpcionDto {
  id: number;
  nombre: string;
  foto: string;
  ruc: string;
}
export interface ClienteSolicitudOpcionDto {
  id: number;
  nombre: string;
  nombrePersonaPrincipal: string;
}

export interface DatoTecaDto {
  telefonoNegocio: string;
  correoNegocio: string;
  urlNegocio: string;
}

export interface DataInicial {
  listaClientes: ClienteSolicitudOpcionDto[];
  listaServicios: OpcionServicioDto[];
  listaPlanes: OpcionServicioDto[];
  listaDispositivos: OpcionDispositivoDto[];
  listaNegocios: UndNegocioOpcionDto[];
  listaPersonaNegocio: Opcion[];
  datoTeca: DatoTecaDto;
}
