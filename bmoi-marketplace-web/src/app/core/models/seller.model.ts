export interface EmpresaOut {
  texto: string;
  pagina: number;
  cantidad: number;
  idPersonaLogeada?: number;
  sortOrden: string;
  sortCampo: string;
}
export interface EmpresaIn {
  id: number;
  tipEmpresa: string;
  razSocial: string;
  rubro: string;
  nomComercial: string;
  ruc: string;
  telefono: string;
  descripcion: string;
  email1: string;
  email2: string;
  celular: string;
  web: string;
  otros: string;
  usuRegistro: string;
  fecRegistro: string;
  usuActualizacion: string;
  fecActualizacion: string;
  monedaCambio: string;
  fecFinal: string;
  totPedidos: number;
  totPedMonto: number;
}

export interface Empresa {
  id: number;
  idpostulante: number;
  tipEmpresa: string;
  razSocial: string;
  rubro: string;
  nomComercial: string;
  feciniActividades: Date;
  ruc: string;
  telefono: string;
  descripcion: string;
  email1: string;
  email2: string;
  celular: string;
  estado?: number;
  website: string;
}

export interface Direccion {
  id: number;
  direccion: string;
  calle: string;
  referencia: string;
  idDepartamento: number;
  idDistrito: number;
  idPais: number;
  idProvincia: number;
}

export interface Persona {
  id: number;
  idSeller?: number;
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
  orden?: number;
}

export interface RedSocial {
  id: number;
  nombre: string;
  url: string;
}

export interface EmpresaMantDto {
  empresa: Empresa;
  direccion: Direccion;
  idPersonaLogeada: number;
  listaPersonas?: Persona[];
  listaRedesSociales: RedSocial[];
  foto?: string;
}
