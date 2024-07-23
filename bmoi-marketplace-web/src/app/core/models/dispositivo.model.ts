import { Opcion } from '@core/soporte/generic.model';

export interface DispositivoOut {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface DispositivoIn {
  id: number;
  idPersonaLogeada: number;
  codigo: string;
  nombre: string;
  productoNombre: string;
  cntFecFin: string;
  cntFecInicio: string;
  cliente: string;
  grupo: string;
  alias: string;
  descripcion: string;
  estDispositivo: string;
  usuRegistro?: string;
  fecRegistro?: string;
  usuActualizacion?: string;
  fecActualizacion?: string;
}

export interface DispositivoMant {
  idPersonaLogeada: number;
  id: number;
  foto?: string;
  codigo: string;
  bateria: number;
  nombre: string;
  grupo: string;
  cliente?: string;
  idProducto: number;
  idProveedor: number;
  reset: boolean;
  caratula: number;
  telemetria: number;
  valorMaximo: number;
  plaConectividad?: string;
  descripcion: string;
  fechaInstalacion?: string;
  estDispositivo: string;
  fechaInicio?: string;
  fechaFin?: string;
  alias: string;
  thingGroup: string;
  subscriptionTopic: string;
  rules: string;
  configSigfox: string;
  latitude: string;
  longitude: string;
  nroSensoresmedida: number;
  listaUdMedida: UndMedidaModelo[];
}

export interface UndMedidaModelo {
  idModelo: number;
  idSensor: number;
  listaSensorModelo: Opcion[];
}

export interface UdsMedida {
  id: number;
  nombre: string;
}
export interface Producto {
  id: number;
  nombre: string;
  foto: string;
  nroSensoresmedida: number;
}
export interface Proveedor {
  id: number;
  nombre: string;
}
