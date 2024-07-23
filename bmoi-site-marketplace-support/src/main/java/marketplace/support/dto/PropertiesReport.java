/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author mpilar
 */
@Data
@NoArgsConstructor
public class PropertiesReport {

    @Data
    @AllArgsConstructor
    public static class ColorRgb {

        private int r, g, b;
    }

    @Data
    @AllArgsConstructor
    public static class RangoColor {

        private int indiceInicial, indiceFinal;
        private ColorRgb colorRgbRango;
    }

    @Data
    @AllArgsConstructor
    public static class FilaCombinada {

        private Fondo fondoEstilo;
        private List<String> listaNombreCampo;

    }

    @Data
    @AllArgsConstructor
    public static class Columna {

        private String nombreColumna;
        private String propiedadColumna;
        private int anchoColumna;
        private Celda celda;

        public Columna(String nombreColumna) {
            this.nombreColumna = nombreColumna;
            this.propiedadColumna = nombreColumna;
        }

        public Columna(String nombreColumna, String propiedadColumna) {
            this.nombreColumna = nombreColumna;
            this.propiedadColumna = propiedadColumna;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Celda {

        private HorizontalAlign alineacionHorizontal;
//        private VerticalAlign alineacionVertical;

    }

    @Data
    @AllArgsConstructor
    public static class Fondo {

        private String nombreColumnaCriterio;
        private ColorRgb colorPar;
        private ColorRgb colorImpar;

    }

    private int prevNroPagina;
    private boolean conGrafico;
    private boolean grafico3d;
    private boolean isField;
    private String titulo;
    private String categoria;
    private String serie;
    private Integer filaCategoria;
    private Integer filaSerie;
    private PlotOrientation orientacion;
    private boolean autosize;
    private boolean ignorarPaginacion;
    private Border border;
    private ColorRgb colorBackgroundHeaderRgb;
    private ColorRgb colorTextoHeaderRgb;
    private ColorRgb colorGrafico;
    private ColorRgb colorMarcoGrafico;
    private FastReportBuilder fastReportBuilder;
    private List<RangoColor> listaRangoColor;
//    private ArrayList<ConditionalStyle> listaCondicionesStyle;
    private Fondo fondoCondicionalEstilo;
    private FilaCombinada filaCombinada;
    private Style styleAllRows;

    public PropertiesReport(boolean isField, boolean autosize) {
        this.isField = isField;
        this.autosize = autosize;
    }

    public PropertiesReport(boolean isField, boolean autosize, boolean ignorarPaginacion) {
        this.isField = isField;
        this.autosize = autosize;
        this.ignorarPaginacion = ignorarPaginacion;
    }

    public PropertiesReport(boolean conGrafico, boolean grafico3d, ColorRgb colorBackgroundHeaderRgb, ColorRgb colorTextoHeaderRgb) {
        this.conGrafico = conGrafico;
        this.grafico3d = grafico3d;
        this.colorBackgroundHeaderRgb = colorBackgroundHeaderRgb;
        this.colorTextoHeaderRgb = colorTextoHeaderRgb;
    }

    public PropertiesReport(boolean conGrafico, boolean grafico3d, PlotOrientation orientacion, ColorRgb colorBackgroundHeaderRgb, ColorRgb colorTextoHeaderRgb) {
        this.conGrafico = conGrafico;
        this.grafico3d = grafico3d;
        this.orientacion = orientacion;
        this.colorBackgroundHeaderRgb = colorBackgroundHeaderRgb;
        this.colorTextoHeaderRgb = colorTextoHeaderRgb;
    }

    public PropertiesReport(boolean conGrafico, boolean grafico3d, String titulo, String categoria, String serie, Integer filaCategoria, Integer filaSerie, PlotOrientation orientacion) {
        this.conGrafico = conGrafico;
        this.grafico3d = grafico3d;
        this.titulo = titulo;
        this.categoria = categoria;
        this.serie = serie;
        this.filaCategoria = filaCategoria;
        this.filaSerie = filaSerie;
        this.orientacion = orientacion;
    }

    public PropertiesReport(FastReportBuilder fastReportBuilder, PlotOrientation orientacion, String titulo, String categoria, String serie, Integer filaCategoria, Integer filaSerie, ColorRgb colorGrafico) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.serie = serie;
        this.filaCategoria = filaCategoria;
        this.filaSerie = filaSerie;
        this.orientacion = orientacion;
        this.fastReportBuilder = fastReportBuilder;
        this.colorGrafico = colorGrafico;
    }

}
