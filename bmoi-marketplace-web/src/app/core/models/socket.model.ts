import { ConsumoHistoricoOutDto } from './reporte-dispositivo.model';

export interface SocketOutDto {
  topic: string;
  filter: ConsumoHistoricoOutDto;
}
export interface SocketCloseOutDto {
  topic: string;
  device: string;
}
