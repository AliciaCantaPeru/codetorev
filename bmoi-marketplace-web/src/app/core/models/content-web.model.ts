export interface ContenidoWebDto {
  id: number;
  interface1: string;
  pagina: string;
  seccion: string;
  bloque: string;
  post: string;
  contenido: string;
  html: string;
  orden: number;
}

export interface SlideContentDto {
  id: number;
  interface1: string;
  pagina: string;
  seccion: string;
  bloque: string;
  urlpath: string;
  titulo1: string;
  titulo2: string;
  titulo3: string;
  orden: number;
}

export interface ContenidWebDto {
  listaContent: ContenidoWebDto[];
  listaSlide: SlideContentDto[];
}
