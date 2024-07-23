export interface ColaboradorClienteOut {
  idPersonaLogeada: number;
  idCliente: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface ColaboradorClienteIn {
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
  nombres: string;
  celular: string;
  telFijo: string;
  foto: string;
}

export interface ColaboradorClienteMant {
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
  celular1?: string;
  celular2?: string;
  cargo?: string;
  cntPrincipal?: number;
  estado?: number;
  nombres: string;
  celular: string;
  telFijo: string;
  foto: string;
  orden?: number;
}
