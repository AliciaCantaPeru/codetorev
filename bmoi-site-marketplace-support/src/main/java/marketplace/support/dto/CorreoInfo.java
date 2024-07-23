/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class CorreoInfo {

    private String correoDestino;
    private String contenido;
    private List<ArchivoAdjunto> archivosAdjuntos;
    private String asunto;
    private Map<String, String> parametros;
    private CorreoConf correoConf;

    @Data
    public static class CorreoConf {

        private String correo;
        private String clave;
        private String tipoContenido;
        private List<Property> propertys;

    }

    @Data
    public static class Property {

        private String key;
        private String value;
    }

    @Data
    @AllArgsConstructor
    public static class ArchivoAdjunto {

        byte[] file;
        String nombre;
    }
}
