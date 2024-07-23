import { MenuGrupoCategoriaDto } from './general.model';

export interface DimensionMantDto {
  id: number;
  altura: number;
  anchura: number;
  peso: number;
  profundida: number;
}

export interface ProductoImagenDto {
  id?: number;
  idDigImgSubImg: number;
}

export interface ProductoImagenPropioDto {
  id?: number;
  idDigImgSubImg?: number;
  url: string;
  estado: boolean;
  idGrupo?: number;
  idCategoria?: number;
}

export interface ListaMultimediaMantDto {
  descripcion?: string;
  grdMultimedia?: string;
  id?: number;
  miniatura?: boolean;
}

export interface ListaPersonalizacionMantDto {
  id: number;
  idDigtipopersonalizacion: number;
  descripcion: string;
}

export interface ListaVarianteMantDto {
  id: number;
  idTalla: number;
  codTalla: number;
  idColor: number;
  codColor: number;
  preciolista: number;
  stock: number;
}
export interface ProductoCategoriaDto {
  id?: number;
  idCategoriaGrupomenu: number;
}

export interface ProductoMantDto {
  avgstar: number;
  descripcion: string;
  destacado: number;
  dscMultimedia1: string;
  dscMultimedia2: string;
  enoferta: string;
  enviogratis: number;
  estado: number;
  f2: number;
  f7: string;
  f8: string;
  f9: string;
  id: number;
  idBrand: number;
  idSeller: number;
  idSellerchat: number;
  codigoMenu: string;
  idUsuarioLogeado: number;
  impuesto: string;
  impuestoporcentaje: number;
  iniciodigital: number;
  dimension: DimensionMantDto;
  listaImg: ProductoImagenDto[];
  listaImgPropio: ProductoImagenPropioDto[];
  listaGrupoCategoria: MenuGrupoCategoriaDto[];
  listaMultimedia: ListaMultimediaMantDto[];
  listaPersonalizacion: ListaPersonalizacionMantDto[];
  listaVariante: ListaVarianteMantDto[];
  listaCategorias: ProductoCategoriaDto[];
  moneda: string;
  multimedia1: string;
  multimedia2: string;
  nombrecorto: string;
  nroSegmentospersonalizable: number;
  onlinestatus: boolean;
  personalizable: boolean;
  preciobasico: number;
  precioenvio: number;
  preciolista: number;
  productonuevo: string;
  sku: string;
  idPrdpadre: number;
  stock: number;
  titulo: string;
  unidadmedida: string;
  descuentoProdNuevo: boolean;
  porcentajeProdNuevo: number;
  fechaInicioProdNuevo: Date;
  fechaFinProdNuevo: Date;
}

export interface ProductoListaDto {
  avgstar: number;
  descripcion: string;
  destacado: number;
  dscMultimedia1: string;
  dscMultimedia2: string;
  enoferta: string;
  enviogratis: number;
  estado: number;
  f2: number;
  f7: string;
  f8: string;
  f9: string;
  id: number;
  idSellerchat: number;
  impuesto: string;
  impuestoporcentaje: number;
  iniciodigital: number;
  moneda: string;
  multimedia1: string;
  multimedia2: string;
  nombrecorto: string;
  nroSegmentospersonalizable: number;
  onlinestatus: number;
  personalizable: number;
  preciobasico: number;
  precioenvio: number;
  preciolista: number;
  productonuevo: string;
  sku: string;
  idPrdpadre: number;
  stock: number;
  titulo: string;
  unidadmedida: string;
}

export interface ProductoListaFiltroDto {
  idPersonaLogeada: number;
  texto: string;
  seccion: number;
  pagina: number;
  cantidad: number;
  sortOrden: string;
  sortCampo: string;
}
