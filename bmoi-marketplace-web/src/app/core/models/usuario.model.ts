export interface UsuarioOut {
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
  idPersonaLogeada: number;
}
export interface UsuarioIn {
  cliente: string;
  rol: string;
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

export interface UsuarioMantDto {
  id: number;
  email: string;
  estado: number;
  idSellerPersona: number;
  idRol: number;
  idSeller?: number;
  idPersonaLogeada: number;
}
export interface OpcionPersona {
  id: number;
  email: string;
  nombre: string;
}
