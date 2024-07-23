export interface UsuarioBmoiOut {
  texto: string;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
  idPersonaLogeada: number;
}
export interface UsuarioBmoiIn {
  id: number;
  rol: string;
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
  codigopais: string;
}

export interface UsuarioBmoiMantDto {
  id: number;
  email: string;
  contrasenia?: string;
  estado: number;
  idPersona: number;
  idRol: number;
  idPersonaLogeada?: number;
}
export interface OpcionPersonaBmoi {
  id: number;
  email: string;
  nombre: string;
}
