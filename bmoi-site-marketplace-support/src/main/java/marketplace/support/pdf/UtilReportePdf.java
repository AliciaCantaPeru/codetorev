/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.pdf;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJBar3DChartBuilder;
import ar.com.fdvs.dj.domain.chart.builder.DJBarChartBuilder;
import ar.com.fdvs.dj.domain.chart.plot.DJAxisFormat;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.domain.entities.columns.SimpleColumn;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import ar.com.fdvs.dj.util.SortUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jfree.chart.plot.PlotOrientation;
import marketplace.support.dto.PropertiesReport;

/**
 *
 * @author mpilar
 */
@Log4j2
public class UtilReportePdf {

    private List<PropertiesReport.Columna> listaCabezera = new ArrayList();
    private Map params = new HashMap();
    private String rutaPlantilla;
    private Collection<Map<String, Object>> data = new ArrayList<>();
    private DynamicReport dr;
    private JasperReport jr;
    public FastReportBuilder builder;
    public static final PropertiesReport.ColorRgb COLOR_TEXTO_BLANCO_RGB = new PropertiesReport.ColorRgb(255, 255, 255);
    public static final PropertiesReport.ColorRgb COLOR_TEXTO_NEGRO_RGB = new PropertiesReport.ColorRgb(0, 0, 0);

    /**
     * Solo se usa para unir reportes.
     */
    public UtilReportePdf() {
    }

    /**
     * inyectar dato necesario del reporte
     *
     * @param listaCabezera
     * @param data
     * @param params
     * @param rutaPlantillaJrxml
     */
    public UtilReportePdf(List<PropertiesReport.Columna> listaCabezera, Collection<Map<String, Object>> data, Map<String, String> params, String rutaPlantillaJrxml) {
        this.listaCabezera = listaCabezera;
        this.data = data;
        this.params = params;
        this.rutaPlantilla = rutaPlantillaJrxml;
    }

    /**
     * Construir el reporte indicando si hay grafico si es con grafico true y si
     * se requiere los demas parametros caso contrario null
     *
     * @param propertiesReport propiedades para graficar el reporte
     */
    public void ConstruirReporteExcel(PropertiesReport propertiesReport) {
        try {
            obtenerReporte(propertiesReport);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * Construir el reporte indicando si hay grafico si es con grafico true y se
     * requiere los demas parametros caso contrario null
     *
     * @param propertiesReport propiedades para graficar el reporte
     */
    public void construirReportePDF(PropertiesReport propertiesReport) {
        try {
            obtenerReporte(propertiesReport);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * generar el reporte
     */
    public JasperPrint generarReporte() throws Exception {
        dr = builder.build();
        JRDataSource ds = getData();
        jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
        JasperPrint jp;
        if (ds != null) {
            jp = JasperFillManager.fillReport(jr, params, ds);
        } else {
            jp = JasperFillManager.fillReport(jr, params);
        }
        return jp;
    }

    private LayoutManager getLayoutManager() {
        return new ClassicLayoutManager();
    }

    /**
     * Recibe un objeto de propiedades
     *
     * @param propertiesReport
     * @throws Exception
     */
    public void obtenerReporte(PropertiesReport propertiesReport) throws Exception {
        builder = new FastReportBuilder();
//        report.setTitle("EQUIPOS");
        builder.setMargins(10, 10, 10, 10);

        if (rutaPlantilla != null) {
            log.error(":::::::::::::::::::::::::::::::::::::::" + getCurrentDirectory());
            builder.setTemplateFile(rutaPlantilla);
        }
        if (propertiesReport.isField()) {
            generarField();
        } else {
            generarColumnas(propertiesReport);
            builder.setUseFullPageWidth(true);
            builder.setPrintColumnNames(true);
        }

        builder.setIgnorePagination(propertiesReport.isIgnorarPaginacion()); // para excel una sola pagina (hoja)

        if (propertiesReport.isConGrafico()) {
            if (propertiesReport.isGrafico3d()) {
                grafico3d(propertiesReport);
            } else {
                grafico2d(propertiesReport);
            }
        }

        generarColorRango(propertiesReport);
    }

    /**
     * Generar las columnas para las plantillas personalizadas
     */
    private void generarField() {
        listaCabezera.stream().map((columna) -> columna.getNombreColumna()).forEachOrdered((nombreColumna) -> {
            builder.addField(nombreColumna, Object.class.getName());
        });
    }

    /**
     * Generar los rangos de celdas con color seteado
     *
     * @param propertiesReport
     */
    private void generarColorRango(PropertiesReport propertiesReport) {
        if (propertiesReport.getListaRangoColor() != null && propertiesReport.getListaRangoColor().size() > 0) {

            for (PropertiesReport.RangoColor rangoColor : propertiesReport.getListaRangoColor()) {

                int indiceInicial = rangoColor.getIndiceInicial();
                int indiceFinal = rangoColor.getIndiceFinal();

                for (int i = indiceInicial; i < builder.getColumns().size(); i++) {
                    SimpleColumn simpleColumn = (SimpleColumn) builder.getColumns().get(i);
                    Style style = new Style();
                    Color color = new Color(rangoColor.getColorRgbRango().getR(), rangoColor.getColorRgbRango().getG(), rangoColor.getColorRgbRango().getB());
                    style.setBackgroundColor(color);
                    simpleColumn.setHeaderStyle(style);
                    if (i == indiceFinal) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Generar columnas para la tabla
     *
     * @param propertiesReport
     */
    private void generarColumnas(PropertiesReport propertiesReport) {
        AtomicInteger indice = new AtomicInteger(0);
        listaCabezera.stream().map((columna) -> {
            String nombreColumna = columna.getNombreColumna();
            String propiedadColumna = columna.getPropiedadColumna();
            int width = columna.getAnchoColumna();
            PropertiesReport.Celda celda = columna.getCelda();
            AbstractColumn c1;
            ColumnBuilder columnBuilder = new ColumnBuilder();
            columnBuilder.setColumnProperty(propiedadColumna, Object.class.getName());
            columnBuilder.setTitle(nombreColumna);
            columnBuilder.setWidth(width);
            columnBuilder.setFixedWidth(propertiesReport.isAutosize());
            columnBuilder.setStyle(propertiesReport.getStyleAllRows() != null ? propertiesReport.getStyleAllRows() : estiloFila(celda, propertiesReport.getBorder()));
            String columnaAgrupar = propertiesReport.getFilaCombinada() != null ? propertiesReport.getFilaCombinada().getListaNombreCampo().stream().filter(x -> x.equals(nombreColumna)).findFirst().orElse(null) : null;
            if (propertiesReport.getFondoCondicionalEstilo() != null && columnaAgrupar == null) {
                columnBuilder.addConditionalStyles(generarEstiloCondicional(propertiesReport.getFondoCondicionalEstilo(), celda, propertiesReport.getBorder()));
            }
            if (columnaAgrupar != null) {
                ArrayList<ConditionalStyle> conditionalStyles = generarCondicionalEstiloRowspan(propertiesReport.getFilaCombinada(), celda);
                columnBuilder.addConditionalStyles(conditionalStyles);
            }
            if (propertiesReport.getColorBackgroundHeaderRgb() != null && propertiesReport.getColorTextoHeaderRgb() != null) {
                columnBuilder.setHeaderStyle(estiloHeader(propertiesReport.getColorBackgroundHeaderRgb(), propertiesReport.getColorTextoHeaderRgb(), propertiesReport.getBorder()));
            }
            if (propertiesReport.getFilaSerie() != null && indice.get() == propertiesReport.getFilaSerie()) {
                columnBuilder.setCustomExpression(convertirInteger(nombreColumna));
                columnBuilder.setCustomExpressionForCalculation(convertirInteger(nombreColumna));
                c1 = columnBuilder.build();
            } else {
                c1 = columnBuilder.build();
            }
            return c1;
        }).forEachOrdered((c1) -> {
            indice.incrementAndGet();
            builder.addColumn(c1);
        });
    }

    /**
     * obtener grafico
     *
     * @param propertiesReport
     * @return
     */
    public FastReportBuilder grafico3d(PropertiesReport propertiesReport) {
        DJAxisFormat categoryAxisFormat = new DJAxisFormat(propertiesReport.getCategoria());
//        categoryAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelMask("");
        categoryAxisFormat.setLineColor(Color.DARK_GRAY);

        DJAxisFormat valueAxisFormat = new DJAxisFormat(propertiesReport.getSerie());
//        valueAxisFormat.setLabelFont(Font.SMALL);
        valueAxisFormat.setLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        valueAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelMask("#,##0.0");

        valueAxisFormat.setLineColor(Color.DARK_GRAY);

        PropertyColumn columnaCategoria = propertiesReport.getFastReportBuilder() != null ? (PropertyColumn) propertiesReport.getFastReportBuilder().getColumn(propertiesReport.getFilaCategoria()) : (PropertyColumn) builder.getColumn(propertiesReport.getFilaCategoria());
        AbstractColumn columnaSerie = propertiesReport.getFastReportBuilder() != null ? propertiesReport.getFastReportBuilder().getColumn(propertiesReport.getFilaSerie()) : builder.getColumn(propertiesReport.getFilaSerie());

        Color colorChartRgb = new Color(propertiesReport.getColorGrafico().getR(), propertiesReport.getColorGrafico().getG(), propertiesReport.getColorGrafico().getB());

        DJChart djChart = new DJBar3DChartBuilder()
                //chart     
                .setX(0)
                .setY(30)
                //                .setWidth(500)
                //                .setHeight(500)
                .setCentered(true)
                .setBackColor(Color.LIGHT_GRAY)
                .setShowLegend(false)
                .setPosition(DJChartOptions.POSITION_FOOTER)
                .setTitle(propertiesReport.getTitulo())
                .setTitleColor(Color.DARK_GRAY)
                .setTitleFont(Font.VERDANA_SMALL)
                //                .setSubtitle("subtitle")
                .setSubtitleColor(Color.DARK_GRAY)
                .setSubtitleFont(Font.COURIER_NEW_BIG_BOLD)
                .setLegendColor(Color.DARK_GRAY)
                .setLegendFont(Font.COURIER_NEW_MEDIUM_BOLD)
                .setLegendBackgroundColor(Color.WHITE)
                .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
                .setTitlePosition(DJChartOptions.EDGE_TOP)
                .setLineStyle(DJChartOptions.LINE_STYLE_SOLID)
                .setLineColor(Color.RED)
                .setLineWidth(7)
                .setLineColor(colorChartRgb)
                .setPadding(5)
                //dataset
                .setOrientation(propertiesReport.getOrientacion())
                .setCategory((PropertyColumn) columnaCategoria)
                .addSerie(columnaSerie)
                .setSeriesColors(listarColoresReporte(colorChartRgb))
                //                .setUseSeriesAsCategory(true)
                //plot
                //                                .setXOffset(10)
                //                .setyOffset(10)
                .setShowLabels(false)
                .setCategoryAxisFormat(categoryAxisFormat)
                .setValueAxisFormat(valueAxisFormat)
                .build();
        if (propertiesReport.getFastReportBuilder() != null) {
            propertiesReport.getFastReportBuilder().addChart(djChart);
            return propertiesReport.getFastReportBuilder();
        } else {
            builder.addChart(djChart);
            return builder;
        }
    }

    /**
     * obtener grafico
     *
     * @param propertiesReport
     * @return
     */
    public FastReportBuilder grafico2d(PropertiesReport propertiesReport) {
        DJAxisFormat categoryAxisFormat = new DJAxisFormat(propertiesReport.getCategoria());
//        categoryAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelMask("");
        categoryAxisFormat.setLineColor(Color.DARK_GRAY);

        DJAxisFormat valueAxisFormat = new DJAxisFormat(propertiesReport.getSerie());
//        valueAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        valueAxisFormat.setLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        valueAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelMask("#,##0.0");

        valueAxisFormat.setLineColor(Color.DARK_GRAY);

        PropertyColumn columnaCategoria = propertiesReport.getFastReportBuilder() != null ? (PropertyColumn) propertiesReport.getFastReportBuilder().getColumn(propertiesReport.getFilaCategoria()) : (PropertyColumn) builder.getColumn(propertiesReport.getFilaCategoria());
        AbstractColumn columnaSerie = propertiesReport.getFastReportBuilder() != null ? propertiesReport.getFastReportBuilder().getColumn(propertiesReport.getFilaSerie()) : builder.getColumn(propertiesReport.getFilaSerie());

        Color colorChartRgb = new Color(propertiesReport.getColorGrafico().getR(), propertiesReport.getColorGrafico().getG(), propertiesReport.getColorGrafico().getB());

        DJChart djChart = new DJBarChartBuilder()
                //chart     
                .setX(0)
                .setY(30)
                //                .setWidth(500)
                //                .setHeight(500)
                .setCentered(true)
                .setBackColor(Color.LIGHT_GRAY)
                .setShowLegend(false)
                .setPosition(DJChartOptions.POSITION_FOOTER)
                .setTitle(propertiesReport.getTitulo())
                .setTitleColor(Color.DARK_GRAY)
                .setTitleFont(Font.VERDANA_SMALL)
                //                .setSubtitle("subtitle")
                .setSubtitleColor(Color.DARK_GRAY)
                .setSubtitleFont(Font.COURIER_NEW_BIG_BOLD)
                .setLegendColor(Color.DARK_GRAY)
                .setLegendFont(Font.COURIER_NEW_MEDIUM_BOLD)
                .setLegendBackgroundColor(Color.WHITE)
                .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
                .setTitlePosition(DJChartOptions.EDGE_TOP)
                .setLineStyle(DJChartOptions.LINE_STYLE_SOLID)
                .setLineColor(Color.RED)
                .setLineWidth(7)
                .setLineColor(colorChartRgb)
                .setPadding(5)
                //dataset
                .setOrientation(propertiesReport.getOrientacion())
                .setCategory((PropertyColumn) columnaCategoria)
                .addSerie(columnaSerie)
                .setSeriesColors(listarColoresReporte(colorChartRgb))
                //                .setUseSeriesAsCategory(true)
                //plot
                //                                .setXOffset(10)
                //                .setyOffset(10)
                .setShowLabels(false)
                .setCategoryAxisFormat(categoryAxisFormat)
                .setValueAxisFormat(valueAxisFormat)
                .build();
        if (propertiesReport.getFastReportBuilder() != null) {
            propertiesReport.getFastReportBuilder().addChart(djChart);
            return propertiesReport.getFastReportBuilder();
        } else {
            builder.addChart(djChart);
            return builder;
        }
    }

    private List<Color> listarColoresReporte(Color colorChart) {
//        Color colorVerde = new Color(22, 160, 133);
//        Color colorAzul = new Color(34, 166, 179);
//        Color colorNaranja = new Color(230, 126, 34);
        return Arrays.asList(colorChart);
    }

    /**
     * creacion del grafico sin mostrar tabla requiere creacion del pdf base
     * ncontreras@mimp.gob.pe
     *
     * @param listaCategoriaSerie
     * @param tituloGrafico
     * @param nombreCategoria
     * @param nombreSerie
     * @param colorGrafico
     * @param numeroPaginaInicial
     * @return
     */
    public JasperPrint obtenerReporteSoloGrafico(List<Map<String, Object>> listaCategoriaSerie, String tituloGrafico, String nombreCategoria, String nombreSerie, PlotOrientation orientacion, PropertiesReport.ColorRgb colorGrafico, Integer numeroPaginaInicial) {
        try {

            FastReportBuilder frb = new FastReportBuilder();
            frb.setMargins(10, 10, 10, 10);
            if (rutaPlantilla != null) {
                frb.setTemplateFile(rutaPlantilla);
            }
            AbstractColumn columnaCategoria = ColumnBuilder.getInstance()
                    .setTitle("HRT")
                    .setColumnProperty("CATEGORIA", Object.class.getName())
                    .build();
            AbstractColumn columnaSerie = ColumnBuilder.getInstance()
                    .setTitle("TOTAL")
                    .setColumnProperty("SERIE", Object.class.getName())
                    .setCustomExpression(convertirInteger("SERIE"))
                    //                    .setCustomExpressionForCalculation(convertirInteger("SERIE"))
                    .build();
            frb.addColumn(columnaCategoria);
            frb.addColumn(columnaSerie);
            frb.setUseFullPageWidth(true);
            grafico3d(new PropertiesReport(frb, orientacion, tituloGrafico, nombreCategoria, nombreSerie, 0, 1, colorGrafico));
            DynamicReport drtemp = frb.build();
            JRDataSource dstemp = getDataCalculado(listaCategoriaSerie, drtemp);
            if (params != null) {
                params.put("prevNroPagina", String.valueOf(numeroPaginaInicial != null ? numeroPaginaInicial : "0"));
            }
            JasperReport jrtemp = DynamicJasperHelper.generateJasperReport(drtemp, new NoTableLayoutManager(), params);
            JasperPrint jsp;
            if (dstemp != null) {
                jsp = JasperFillManager.fillReport(jrtemp, params, dstemp);
            } else {
                jsp = JasperFillManager.fillReport(jrtemp, params);
            }
            return jsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ocultamos la tabla
     *
     */
    public class NoTableLayoutManager extends ClassicLayoutManager {

        @Override
        protected List<AbstractColumn> getVisibleColumns() {
            return new ArrayList<>(); // hide all columns
        }
    }

    /**
     * obtener data de la tabla
     *
     * @return
     */
    public JRDataSource getData() {
        Collection<Map<String, Object>> dataOneReport = data;
        return new JRBeanCollectionDataSource(dataOneReport);
    }

    /**
     * obtener data para el grafico calculado
     *
     * @param listaCatergoriaSerie
     * @param dinamicReport
     * @return
     */
    public JRDataSource getDataCalculado(List<Map<String, Object>> listaCatergoriaSerie, DynamicReport dinamicReport) {
        Collection<Map<String, Object>> dataOneReport = listaCatergoriaSerie;
        dataOneReport = SortUtils.sortCollection(dataOneReport, dinamicReport.getColumns());
        //Create a JRDataSource, the Collection used
        return new JRBeanCollectionDataSource(dataOneReport);
    }

    /**
     * obtener estilo de cabezera
     *
     * @return
     */
    public Style estiloHeader(PropertiesReport.ColorRgb colorBackground, PropertiesReport.ColorRgb colorTextoRgb, Border border) {
        Style estiloHeader = new Style();
        Font fuente = new Font();
        fuente.setFontSize(8);
        fuente.setBold(true);
        estiloHeader.setHorizontalAlign(HorizontalAlign.CENTER);

        Color colorBackgroundHeader = new Color(colorBackground.getR(), colorBackground.getG(), colorBackground.getB());
        Color colorTextodHeader = new Color(colorTextoRgb.getR(), colorTextoRgb.getG(), colorTextoRgb.getB());
        estiloHeader.setPaddingLeft(4);
        estiloHeader.setPaddingRight(4);
        estiloHeader.setTextColor(colorTextodHeader);
        estiloHeader.setBackgroundColor(colorBackgroundHeader);
        estiloHeader.setBorderColor(Color.WHITE);
        estiloHeader.setBorder(border == null ? Border.THIN() : border);
        estiloHeader.setFont(fuente);
        estiloHeader.setVerticalAlign(VerticalAlign.MIDDLE);
        estiloHeader.setTransparent(false);
        return estiloHeader;
    }

    /**
     * obtener estilo de fila
     *
     *
     * @return
     */
    public Style estiloFila(PropertiesReport.Celda celda, Border border) {
        Style estiloFila = new Style();
        Font fuente = new Font();
        fuente.setFontSize(7);
        fuente.setBold(false);
        estiloFila.setHorizontalAlign(celda.getAlineacionHorizontal());
        estiloFila.setPaddingLeft(4);
        estiloFila.setPaddingRight(4);
        estiloFila.setBorder(border == null ? Border.THIN() : border);
        estiloFila.setFont(fuente);
        estiloFila.setVerticalAlign(VerticalAlign.MIDDLE);
        return estiloFila;
    }

    /**
     * unir reportes en base al jasperBase
     *
     * @param jasperPrintBase jasper al cual se le agregara los demas
     * jasperprints
     * @param jasperPrints
     * @return
     */
    public JasperPrint unirReporte(JasperPrint jasperPrintBase, JasperPrint... jasperPrints) {
        if (jasperPrintBase != null && jasperPrints != null && jasperPrints.length > 0) {
            for (JasperPrint jasperPrint1 : jasperPrints) {
                for (int j = 0; j < jasperPrint1.getPages().size(); j++) {
                    jasperPrintBase.addPage(jasperPrint1.getPages().get(j));
                }
            }
        }
        return jasperPrintBase;
    }

    /**
     * agrupar filas de la tabla
     *
     * @param columnaCriterio indice de la columna del cual se tomara el
     * criterio de agrupacion
     * @param indiceColumnaSumar indice de las columnas que se totalizaran
     * @param estilo
     */
    public void agruparColumnaPorFila(Style estilo, int columnaCriterio, int indiceColumnaSumar, Border border) {

        GroupBuilder gb1 = new GroupBuilder();
        DJGroup g1 = gb1.build();
        estilo.getFont().setBold(true);
        estilo.setHorizontalAlign(HorizontalAlign.RIGHT);
        gb1.setCriteriaColumn((PropertyColumn) builder.getColumn(columnaCriterio));
        gb1.addFooterVariable(builder.getColumn(indiceColumnaSumar), DJCalculation.SUM, estilo);

        Style estiloTotal = estiloHeader(new PropertiesReport.ColorRgb(248, 203, 173), COLOR_TEXTO_NEGRO_RGB, border);
        estiloTotal.setHorizontalAlign(HorizontalAlign.RIGHT);
        estiloTotal.getFont().setBold(true);
        PropertiesReport.Celda celda = new PropertiesReport.Celda(HorizontalAlign.CENTER);
        Style estiloLeyendaTotal = estiloFila(celda, border);
        estiloLeyendaTotal.getFont().setBold(true);
        builder.addGlobalFooterVariable(builder.getColumn(indiceColumnaSumar), DJCalculation.SUM, estiloTotal);
        builder.setGrandTotalLegend("TOTAL");
        builder.setGrandTotalLegendStyle(estiloLeyendaTotal);
        gb1.setGroupLayout(GroupLayout.VALUE_FOR_EACH);
        builder.addGroup(g1);
    }

    /**
     * convetir campo a integer para poder calcular
     *
     * @param indicecolumna
     * @return
     */
    private CustomExpression convertirInteger(String nombreColumna) {

        return new CustomExpression() {
            @Override
            public Integer evaluate(Map fields, Map variables, Map parameters) {
                Integer valor = 0;
                try {
                    valor = (Integer) fields.get(nombreColumna);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return valor;
            }

            @Override
            public String getClassName() {
                return Integer.class.getName();
            }
        };
    }

    /**
     * Este metodo pintara de forma intercalada las filas par e impar
     *
     * @param nombreColumna
     * @param colorBackgroundPar
     * @param colorBackgroundImPar
     * @return
     */
    public static ArrayList<ConditionalStyle> generarEstiloCondicional(PropertiesReport.Fondo fondoCondicional, PropertiesReport.Celda celda, Border border) {
        Style stylePar = generarStyle(fondoCondicional.getColorPar(), celda, border);
        Style styleImpar = generarStyle(fondoCondicional.getColorImpar(), celda, border);

        ArrayList conditionalStyles = new ArrayList();
        conditionalStyles.add(new ConditionalStyle(new backgroundCondicionPar(fondoCondicional.getNombreColumnaCriterio()), stylePar));
        conditionalStyles.add(new ConditionalStyle(new BackgroundConditionImpar(fondoCondicional.getNombreColumnaCriterio()), styleImpar));
        return conditionalStyles;
    }

    /**
     * Metodo para obtener estilo condicional para combinar filas
     *
     * @param nombreColumna
     * @param colorBackgroundPar color relleno fila par
     * @param colorBackgroundImPar color relleno fila impar
     * @return
     */
    public ArrayList<ConditionalStyle> generarCondicionalEstiloRowspan(PropertiesReport.FilaCombinada filaCombinada, PropertiesReport.Celda celda) {

        Style styleRowHeaderPar = obtenerEstiloRowSpan(filaCombinada.getFondoEstilo().getColorImpar(), celda);
        styleRowHeaderPar.setBorderTop(Border.THIN());
        styleRowHeaderPar.setTextColor(Color.BLACK);

        Style styleRowPar = obtenerEstiloRowSpan(filaCombinada.getFondoEstilo().getColorImpar(), celda);

        Style styleRowHeaderImPar = obtenerEstiloRowSpan(filaCombinada.getFondoEstilo().getColorPar(), celda);
        styleRowHeaderImPar.setBorderTop(Border.THIN());
        styleRowHeaderImPar.setTextColor(Color.BLACK);
        Style styleRowImPar = obtenerEstiloRowSpan(filaCombinada.getFondoEstilo().getColorPar(), celda);

        ArrayList conditionalStyles = new ArrayList();
        conditionalStyles.add(new ConditionalStyle(new StyleRowSpanHeaderImpar(filaCombinada.getFondoEstilo().getNombreColumnaCriterio()), styleRowHeaderPar));
        conditionalStyles.add(new ConditionalStyle(new StyleRowSpanImpar(filaCombinada.getFondoEstilo().getNombreColumnaCriterio()), styleRowPar));
        conditionalStyles.add(new ConditionalStyle(new StyleRowSpanHeaderPar(filaCombinada.getFondoEstilo().getNombreColumnaCriterio()), styleRowHeaderImPar));
        conditionalStyles.add(new ConditionalStyle(new StyleRowSpanPar(filaCombinada.getFondoEstilo().getNombreColumnaCriterio()), styleRowImPar));

        return conditionalStyles;
    }

    /**
     * Este metodo genera los estilos que usara el metodo publico
     * generarEstiloCondicional()
     *
     * @param colorBackground
     * @param celda
     * @return
     */
    public static Style generarStyle(PropertiesReport.ColorRgb colorBackground, PropertiesReport.Celda celda, Border border) {

        Style style = new Style();
        Font fuente = new Font();
        fuente.setFontSize(7);
        fuente.setBold(true);
        style.setHorizontalAlign(celda.getAlineacionHorizontal());
        Color colorBackgroundFila = new Color(colorBackground.getR(), colorBackground.getG(), colorBackground.getB());
        style.setTextColor(Color.BLACK);
        style.setBackgroundColor(colorBackgroundFila);
        style.setBorderColor(Color.WHITE);
        style.setBorder(border == null ? Border.THIN() : border);
        style.setFont(fuente);
        style.setVerticalAlign(VerticalAlign.MIDDLE);
        style.setTransparent(false);
        return style;

    }

    /**
     * Este metodo genera los estilos que usara el metodo publico
     * generarCondicionalEstiloRowspan() para mostrar filas combinadas
     *
     * @param colorBackground
     * @return
     */
    private static Style obtenerEstiloRowSpan(PropertiesReport.ColorRgb colorBackground, PropertiesReport.Celda celda) {

        Color colorBackgroundFila = new Color(colorBackground.getR(), colorBackground.getG(), colorBackground.getB());
        Font fuente = new Font();
        fuente.setFontSize(7);
        fuente.setBold(true);
        fuente.setFontName(Font.VERDANA_SMALL.getFontName());
        Border borderCell = new Border(1);
        borderCell.setColor(colorBackgroundFila);
        borderCell.setLineStyle(Border.BORDER_STYLE_SOLID);
        Style styleRowHeader = new Style();
        styleRowHeader.setTextColor(colorBackgroundFila);
        styleRowHeader.setTransparent(false);
        styleRowHeader.setHorizontalAlign(celda.getAlineacionHorizontal());
        styleRowHeader.setFont(fuente);
        styleRowHeader.setBackgroundColor(colorBackgroundFila);
        styleRowHeader.setTransparency(Transparency.OPAQUE);
        styleRowHeader.setBorderLeft(Border.THIN());
        styleRowHeader.setBorderRight(Border.THIN());
        styleRowHeader.setBorderBottom(borderCell);
        return styleRowHeader;
    }

//    /**
//     * * alinear la columna de la tabla
//     *
//     * @param mapColumnasAlineacion mapa con el indice de columna y su
//     * respectiva alineacion
//     * @param builder
//     * @return
//     */
//    public void alinearColumna(Map<Integer, HorizontalAlign> mapColumnasAlineacion, FastReportBuilder builder) {
//        mapColumnasAlineacion.entrySet().forEach((columna) -> {
//            Integer indiceColumna = columna.getKey();
//            HorizontalAlign alineacionColumna = columna.getValue();
//            AbstractColumn colNum = (AbstractColumn) builder.getColumns().get(indiceColumna);
//            colNum.getStyle().setHorizontalAlign(alineacionColumna);
//        });
//    }
    /**
     * Metodo statico para obtener el objeto columna con sus respectivas
     * propiedades para la elaboracion de la columna de tabla
     *
     * @param nombreColumna
     * @param anchoColumna
     * @param alineacionHorzontal
     * @return
     */
    private static PropertiesReport.Columna obtenerColumna(String nombreColumna, String propiedadColumna, int anchoColumna, HorizontalAlign alineacionHorzontal) {
        PropertiesReport.Columna columna = new PropertiesReport.Columna(nombreColumna, propiedadColumna, anchoColumna, new PropertiesReport.Celda(alineacionHorzontal));
        return columna;
    }

    public static PropertiesReport.Columna obtenerColumnaRigth(String nombreColumna, String propiedadColumna, int anchoColumna) {
        return obtenerColumna(nombreColumna, propiedadColumna, anchoColumna, HorizontalAlign.RIGHT);
    }

    public static PropertiesReport.Columna obtenerColumnaCenter(String nombreColumna, String propiedadColumna, int anchoColumna) {
        return obtenerColumna(nombreColumna, propiedadColumna, anchoColumna, HorizontalAlign.CENTER);
    }

    public static PropertiesReport.Columna obtenerColumnaLeft(String nombreColumna, String propiedadColumna, int anchoColumna) {
        return obtenerColumna(nombreColumna, propiedadColumna, anchoColumna, HorizontalAlign.LEFT);
    }

    String getCurrentDirectory() {
        return this.getClass().getResource("").toString();
    }
}
