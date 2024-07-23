import { IColumnMat } from './util.model';

export interface ReporteDispositivoOutDto {
  idSeller: number;
  idProducto: number;
  negociacion: string;
  filtro: string;
}
export interface DispositivoReporteDto {
  id: number;
  codigo: string;
  grupo: string;
  alias: string;
  codProducto: string;
  latitude: string;
  longitude: string;
}

export interface ConsumoDispositivoDto {
  listaVolumetria: VolumetriaDto[];
  listacolumnas: IColumnMat[];
}

export interface VolumetriaDto {
  fecha: string;
  pulsosValor0: string;
  pulsosValor1: string;
  pulsosValor2: string;
  pulsosValor3: string;
  pulsosValor4: string;
  bateria: string;
  val0: string;
  val1: string;
  val2: string;
  val3: string;
  val4: string;
}

export interface ReporteGraficoDto {
  listaDatoSvg: DataSvgDto[];
  modeloSvg: string;
}

export interface DataSvgDto {
  clave: string;
  valor: string;
  color: string;
  mostrar: boolean;
}

export interface ConsumoHistoricoInDto {
  series: SeriesDto[];
  categories: string[];
  listaUndMedida: string[];
}

export interface SeriesDto {
  name: string;
  data: number[];
  type?: string;
}
export interface ConsumoHistoricoOutDto {
  idCliente: number;
  device: string;
  periodo?: string;
  undMedida?: string;
  fechasFiltro?: Date[];
}
export interface LocationDto {
  latLong: number[];
  fecha: string;
}

export interface GenerarPdfConsumoOutDto {
  consumoDispositivoDto: ConsumoDispositivoDto;
  consumoHistoricoInDto: ConsumoHistoricoOutDto;
  dispositivoReporteDto: DispositivoReporteDto;
  tipo: string;
  imagenChart: string;
}
