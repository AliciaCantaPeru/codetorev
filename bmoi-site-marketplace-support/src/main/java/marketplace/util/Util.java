/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import lombok.extern.log4j.Log4j2;
import org.threeten.extra.YearWeek;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Log4j2
public class Util {

    private static final DateTimeFormatter formaterToDayFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    public static final String SEPARADOR = ";";
    public static final String SEPARADOR_GUION = "-";

    public final ZoneId zonaAmerLima = ZoneId.of("America/Lima");

    public static String unescapeJavascript(String texto) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        return (String) engine.eval("unescape('" + texto + "')");
    }

    public static String escapeJavascript(String texto) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        return (String) engine.eval("escape('" + texto + "')");
    }

    public static String getFechaFilter(String texto) throws Exception {
        String[] HoraSplit = texto.split(" ");
        String[] fechaSplit = HoraSplit[0].split("/");
        String day = fechaSplit.length > 0 ? fechaSplit[0] : "";
        String month = fechaSplit.length > 1 ? fechaSplit[1] : "";
        String year = fechaSplit.length > 2 ? fechaSplit[2] : "";
        String horas = HoraSplit.length == 2 ? " " + HoraSplit[1] : "";
        String guionMonth = month == "" ? "" : "-";
        String guionYear = year == "" ? "" : "-";
        return year + guionYear + month + guionMonth + day + horas;
    }

    public static Date getFecha(String texto) {
        try {
            return formater.parse(texto);
        } catch (ParseException parseException) {
            return null;
        }
    }

    public static String getFechaString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatterFull = new SimpleDateFormat("dd/MM/yyyy");
        return formatterFull.format(date);
    }

    public static String getFechaSku(Date date) {
        SimpleDateFormat formatterFull = new SimpleDateFormat("MMYY");
        return formatterFull.format(date);
    }

    public static String getCorrelativoSku(Integer idSeller, String lastSku, String codigoMenu, String idCategoria, String codCategoria) {
        String fecha = Util.getFechaSku(new Date());
        Integer correlativo = 1;
        if (lastSku != null) {
            String[] splitSku = lastSku.split(Util.SEPARADOR);
            String serie = splitSku.length > 2 ? (splitSku[splitSku.length - 1].substring(4)) : "0";
            correlativo = Integer.valueOf(serie) + 1;
        }
        String correlativoSerieFinal = completarCodigo(correlativo);
        String finalSku = idSeller.toString() + SEPARADOR_GUION + codigoMenu + idCategoria + codCategoria + SEPARADOR_GUION + fecha + correlativoSerieFinal;
        return finalSku;
    }

    public static String getFechaString() {
        SimpleDateFormat formatterFull = new SimpleDateFormat("yyyy/MM/dd-HHmmss");
        return formatterFull.format(new Date());
    }

    public static String completarCodigo(int id) {
        String serie = "00000";
        String serieCompleta = serie + id;
        int indexInicial = serieCompleta.length() - 5;
        String serieFinal = serieCompleta.substring(indexInicial);
        return serieFinal;
    }

    public static void main(String[] args) {
        List<LocalDate> getListaDate = getListaDate(LocalDate.now());
        for (LocalDate date : getListaDate) {
            System.out.println("date + " + date);
        }
    }

    public static List<LocalDate> getListaDate(LocalDate date) {
        List<LocalDate> localDates = new ArrayList<>();
        int year = date.getYear();
        int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        YearWeek yearWeek = YearWeek.of(year, week);
        LocalDate localDateInicio = yearWeek.atDay(DayOfWeek.of(1));
        LocalDate localDateFin = yearWeek.atDay(DayOfWeek.of(7));
        localDates.add(localDateInicio);
        localDates.add(localDateFin.plusDays(1));
        log.error(localDates);
        return localDates;
    }

    public static int getDateAnio(String fecha) {
        Date fechaDate = new Date(fecha);
        return fechaDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().getYear();
    }

    public static String getDate(String fecha) {
        Date fechaDate = new Date(fecha);
        return fechaDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().format(formaterToDayFull);
    }

    public static LocalDateTime getLocalDate(String fecha) {
        Date fechaDate = new Date(fecha);
        return fechaDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate getLocalDate(Date fecha) {
        return fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String getDateMesNombre(String fecha) {
        Date fechaDate = new Date(fecha);
        return fechaDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
    }
}
