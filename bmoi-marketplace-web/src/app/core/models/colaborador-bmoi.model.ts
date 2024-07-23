export interface ColaboradorBmoiOut {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}

export interface ColaboradorBmoiIn {
  id: number;
  nombres: string;
  priApellido: string;
  segApellido: string;
  fecNacimiento: string;
  sexo: string;
  tipDocumento: string;
  numDocumento: number;
  email: string;
  celular: string;
  foto: string;
  codigopais: number;
}

export interface ColaboradorBmoiMant {
  idPersonaLogeada?: number;
  id: number;
  nombres: string;
  priApellido: string;
  segApellido: string;
  fecNacimiento: string;
  sexo: string;
  tipDocumento: string;
  numDocumento: string;
  email: string;
  celular: string;
  foto?: string;
  codigopais: number;
  orden?: number;
}
