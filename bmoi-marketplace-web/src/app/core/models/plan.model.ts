export interface PlanMantDto {
  id: number;
  idPersonaLogeada: number;
  nombre: string;
  descripcion: string;
  costo: number;
  cstVenta: number;
  margen: number;
}

export interface PlanOut {
  cantidad: number;
  idPersonaLogeada: number;
  pagina: number;
  texto: string;
  sortOrden: string;
  sortCampo: string;
}
