export interface AutLoginOut {
  correo: string;
  contrasenia: string;
}

export interface AutUsuarioIn {
  idSeller: number;
  idSellerPersona: number;
  correo: string;
  rol: string;
  nombres: string;
  plan: string;
  temaApp: string;
  empresa: string;
  tipoUsuario: string;
  idRol: number;
}

export interface AutUsuarioOut {
  idPersona: string;
}

export interface TokenDecode {
  aud: string;
  exp: number;
  iat: number;
  sub: string;
  user: AutUsuarioIn;
}

export interface TokenDto {
  token: string;
}

export interface KeyOutDto {
  valor: string;
}

export interface CambiarContraseniaDto {
  key: string;
  contrasenia: string;
}
