/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support;

import marketplace.support.dto.InfoBase64;
import marketplace.support.dto.ResourceInfo;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mpilar
 */
public interface ResourceSupport {

    public InfoBase64 obtenerInfoBase64(String base64) throws Exception;

    public String obtenerRutaBaseRemota();

    public ResourceInfo guardarResource(MultipartFile multipartFile, String rutaBase, Map<String, String> parametros, String nombreResource) throws Exception;

    public void eliminarResource(String ruta, boolean esRutaCompleta) throws Exception;

}
