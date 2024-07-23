export interface ColaboradorSellerOut {
  idPersonaLogeada: number;
  idSeller: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface ColaboradorSellerIn {
  id: number;
  priNombre: string;
  segNombre: string;
  priApellido: string;
  segApellido: string;
  fecNacimiento: string;
  sexo: string;
  tipDocumento: string;
  numDocumento: number;
  email: string;
  celular1: string;
  celular2: string;
  telFijo: string;
  foto: string;
  cargo: string;
  usuRegistro: string;
  fecRegistro: string;
  usuActualizacion: string;
  fecActualizacion: string;
  cntPrincipal: number;
}

export interface ColaboradorSellerMant {
  idPersonaLogeada: number;
  idSeller: number;
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
  telFijo?: string;
  celular1?: string;
  celular2?: string;
  cargo?: string;
  cntPrincipal?: number;
  estado?: number;
  orden?: number;
  foto: string;
}
