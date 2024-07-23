import { ArbolData } from './../../shared/components/c-arbol/c-arbol.component';
import { FiltroDateDto } from '@core/models/util.model';
import { UUID } from 'angular2-uuid';
import { getISOWeek } from 'date-fns';
export class Util {
  public static readonly PDF_WORD = ['PDF', 'WORD'];
  public static readonly PDF_EXCEL = ['PDF', 'EXCEL'];

  public static getFormatoIso(fechaString: string): string {
    const dia = fechaString.substring(0, 2);
    const mes = fechaString.substring(3, 5);
    const anio = fechaString.substring(6, 10);
    const fecha = anio + '-' + mes + '-' + dia + ' 00:00';
    return fecha;
  }
  public static getDecimal(monto: number): number {
    if (monto == undefined || monto == null || isNaN(monto)) {
      return parseFloat((0.0).toFixed(2));
    }
    return parseFloat(Number(monto).toFixed(2));
  }
  public static getSerieId(id: number): string {
    if (id === -1) {
      return '00000';
    }
    const serie = '00000';
    const serieTotal = serie + id;
    const indexInicial = serieTotal.length - 5;
    const serieFinal = serieTotal.substring(indexInicial);
    return serieFinal;
  }
  public static getFilterDate(tipoFiltro: string, date: Date): FiltroDateDto {
    let filtorDate: FiltroDateDto;
    if (tipoFiltro === 'week') {
      filtorDate = {
        week: getISOWeek(date),
        month: date.getMonth() + 1,
        year: date.getFullYear(),
      };
    }
    if (tipoFiltro === 'month') {
      filtorDate = {
        week: null,
        month: date.getMonth() + 1,
        year: date.getFullYear(),
      };
    }
    if (tipoFiltro === 'year') {
      filtorDate = {
        week: null,
        month: null,
        year: date.getFullYear(),
      };
    }
    return filtorDate;
  }
  public static pressSoloLetras(event) {
    return event.charCode >= 48 && event.charCode <= 57 ? false : true;
  }
  public static getFactorSerie(maxSerie: number) {
    let cantidadDigitos =
      Math.max(Math.floor(Math.log10(Math.abs(maxSerie))), 0) + 1 - 3;
    let factor = '1.';
    while (cantidadDigitos > 0) {
      factor = factor + '0';
      cantidadDigitos--;
    }
    factor = factor + '1';
    return Number(factor);
  }
  public static obtenerUUID(codProducto) {
    return '/topic/' + codProducto + '/' + UUID.UUID();
  }

  public static obtenerUrlYoutube(url: string) {
    let posInicial = url.indexOf(':');
    let posInicialParam = url.indexOf('?') + 1;
    let params = url.slice(posInicialParam).split('&');
    let linkYouYube = url.slice(0, posInicialParam) + params[0];
    const atribAutoPlay = false ? '&amp;autoplay=1' : '';
    const atributos = '?rel=0&amp;controls=0' + atribAutoPlay;
    linkYouYube = linkYouYube.replace('watch?v=', 'embed/');
    return linkYouYube;
  }

  public static seleccionTotal(hijos: ArbolData<any>[]): boolean {
    if (hijos && hijos !== null && hijos.length > 0) {
      let seleccionTotal = hijos.every((d) => d.checked);
      if (seleccionTotal && hijos && hijos.length > 0) {
        hijos.forEach((h) => {
          if (seleccionTotal) {
            seleccionTotal = this.seleccionTotal(h.hijos);
          }
        });
      }
      return seleccionTotal;
    }
    return true;
  }

  public static seleccionParcial(hijos: ArbolData<any>[]): boolean {
    if (hijos && hijos !== null && hijos.length > 0) {
      let seleccionParcial = hijos.some((d) => d.checked);
      if (!seleccionParcial && hijos && hijos.length > 0) {
        hijos.forEach((h) => {
          if (!seleccionParcial) {
            seleccionParcial = this.seleccionParcial(h.hijos);
          }
        });
      }
      return seleccionParcial;
    }
    return false;
  }
  public static getMensaje(cantidad: number) {
    if (cantidad > 1) {
      return '¿Seguro que desea eliminar estos registros?';
    }
    return '¿Seguro que desea eliminar este registro?';
  }
}
