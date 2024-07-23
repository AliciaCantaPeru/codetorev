export interface GeneralDto {
  listaParametro: ParametroIn[];
  listaOpciones: OpcioIn[];
}

export interface ParametroIn {
  clave: string;
  valor: string;
  tipo: string;
  interface1: string;
  descripcion: string;
}

export interface OpcioIn {
  id: number;
  fieldname: string;
  fieldvalue: string;
  descripcion: string;
}

export interface ValorCambioIn {
  id: string;
  moneda: string;
  valCompra: string;
  valVenta: string;
  simbolo: string;
}

export interface GeneralProductoDto {
  listaColor: OptionSelectDto[];
  listaMarca: OptionSelectDto[];
  listaMenuDisenio: OptionSelectDto[];
  listaMenuOpcion: OptionSelectDto[];
  listaProductoPadre: OptionSelectDto[];
  listaTalla: OptionSelectDto[];
  listaTipoPersonalizacion: OptionSelectDto[];
  listaImagen: ImagenDto[];
}

export interface OptionSelectDto {
  codigo: string;
  id: number;
  img: string;
  nombre: string;
  descripcion: string;
}

export interface MenuGrupoCategoriaDto {
  id: number;
  nombre: string;
  codigo: string;
  listaHijos: MenuGrupoCategoriaDto[];
}
export interface ImagenDto {
  idDigImgSubImg: number;
  idSubcategoria: number;
  url: string;
}
