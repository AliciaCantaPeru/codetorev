export interface ServicioOut {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface ServicioMant {
  id: number;
  nombre: string;
  descripcion: string;
  costo: number;
  cstVenta: number;
  tipo: string;
  margen: number;
  idPersonaLogeada: number;
}
