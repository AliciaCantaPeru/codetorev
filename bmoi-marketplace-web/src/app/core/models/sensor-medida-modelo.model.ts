export interface SensorMedidaModeloMantDto {
  id: number;
  idPersonaLogeada: number;
  sensor: string;
  modelo: string;
  dscTecnica: string;
  homologacion: string;
  fabricante: string;
  especificaciones: string;
  svg: string;
  fieldValue: string;
  nomEqv0: string;
  nomEqv1: string;
  nomEqv2: string;
  nomEqv3: string;
  nomEqv4: string;
  valEqv0: string;
  valEqv1: string;
  valEqv2: string;
  valEqv3: string;
  valEqv4: string;
  idsensormedida: string;
}

export interface SensorMedidaModeloOutDto {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortCampo: string;
  sortOrden: string;
}
