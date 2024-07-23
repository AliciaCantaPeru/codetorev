export interface SensorMedidaMantDto {
  id?: number;
  idPersonaLogeada: number;
  sensor: string;
  variableMedida: string;
  udsMedida: string;
  fieldName: string;
  tipoCalculo: string;
  precisionVariableMedida: string;
  principiosFuncionamiento: string;
  voltajeFuncionamiento: string;
  senialSalida: string;
  foto?: string;
  descripcion: string;
  factoresConversion: string;
  tramaData: string;
}

export interface SensorMedidaOutDto {
  idPersonaLogeada: number;
  texto: string;
  pagina: number;
  cantidad: number;
  sortCampo: string;
  sortOrden: string;
}
