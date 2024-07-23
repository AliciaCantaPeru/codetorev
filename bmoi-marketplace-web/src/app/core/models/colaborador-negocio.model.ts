export interface ColaboradorNegocioOut {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface ColaboradorNegocioIn {
  id: number;
  nombres: string;
  priApellido: string;
  segApellido: string;
  fecNacimiento: string;
  genero: string;
  tipDocumento: string;
  numDocumento: number;
  email: string;
  celular: string;
  telFijo: string;
  foto: string;
  cargo: string;
  usuRegistro: string;
  fecRegistro: string;
  usuActualizacion: string;
  fecActualizacion: string;
  cntPrincipal: number;
}

export interface ColaboradorNegocioMant {
  idPersonaLogeada: number;
  id: number;
  priNombre?: string;
  segNombre?: string;
  priApellido?: string;
  segApellido?: string;
  fecNacimiento?: string;
  sexo?: string;
  tipDocumento?: string;
  numDocumento?: string;
  email?: string;
  celular1?: string;
  celular2?: string;
  cargo?: string;
  cntPrincipal?: number;
  estado?: number;
  orden?: number;
}
