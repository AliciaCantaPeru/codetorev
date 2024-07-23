export interface TipoCambioOut {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}
export interface TipoCambioMant {
  id: number;
  idPersonaLogeada: number;
  moneda: string;
  valCompra: number;
  valVenta: number;
  fecha?: string;
}
