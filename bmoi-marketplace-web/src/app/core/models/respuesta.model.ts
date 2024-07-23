export interface Respuesta<D = undefined> {
  codigo: number;
  dato: D;
  mensaje: string;
  mensajeDev: string;
}
