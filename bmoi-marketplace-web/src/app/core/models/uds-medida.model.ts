export interface UnidadMedidaMantDto {
  id: number;
  idPersonaLogeada?: number;
  unidad: string;
  magnitud: string;
  prefijo: string;
  simbolo: string;
  valor: string;
  equivalencias: string;
  descripcion: string;
}

export interface UnidadMedidaOutDto {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortCampo: string;
  sortOrden: string;
}
