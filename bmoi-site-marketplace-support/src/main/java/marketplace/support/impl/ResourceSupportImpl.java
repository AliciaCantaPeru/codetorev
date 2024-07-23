/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.impl;

import marketplace.support.ResourceSupport;
import marketplace.support.dto.InfoBase64;
import marketplace.support.dto.ResourceInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResourceSupportImpl implements ResourceSupport {

    @Value("${rts.multimedia.ruta-base-remota:}")
    private String rutaBaseRemota;

    @Value("${spring.profiles.active:}")
    private String perfilActivo;

    @Override
    public InfoBase64 obtenerInfoBase64(String base64) throws Exception {
        String[] xBase64 = base64.split(",");
        String base64x0 = xBase64[0];
        String base64x1 = xBase64[1];
        String ext = obtenerExtension(base64x0);
        String tipo = obtenerTipo(base64x0);
        byte[] data = Base64.getDecoder().decode(base64x1);
        return new InfoBase64(data, ext, tipo);
    }

    @Override
    public String obtenerRutaBaseRemota() {
        if (perfilActivo.equals("dev")) {
            return System.getProperty("user.home");
        }
        return rutaBaseRemota;
    }

    @Override
    public ResourceInfo guardarResource(MultipartFile multipartFile, String rutaBase, Map<String, String> parametros, String nombreResource) throws Exception {
        ResourceInfo info = new ResourceInfo();
        info.setNombre(nombreResource != null ? nombreResource : UUID.randomUUID().toString());
        info.setExtension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        info.setRutaBase(obtenerRutaFinal(parametros, rutaBase));
        info.setRutaCompleta(this.obtenerRutaBaseRemota());
        String rutaCarpeta = this.obtenerRutaBaseRemota().concat(info.getRutaBase());
        System.out.println("...........file guardando ..." + info.getRutaCompleta());
        if (!multipartFile.isEmpty()) {
            byte[] bytes = multipartFile.getBytes();
            File f = new File(rutaCarpeta);
            if (!f.exists()) {
                f.mkdirs();
            }
            try (OutputStream stream = new FileOutputStream(info.getRutaCompleta())) {
                stream.write(bytes);
                stream.close();
            }
        }
        System.out.println("...........file guardando exitoso" + info.getRutaCompleta());
        return info;
    }

    @Override
    public void eliminarResource(String ruta, boolean esRutaCompleta) throws Exception {
        String rutaFinal = esRutaCompleta ? ruta : this.obtenerRutaBaseRemota().concat(ruta);
        System.out.println("........file eliminando ... " + rutaFinal);
        File f = new File(this.obtenerRutaBaseRemota().concat(rutaFinal));
        if (f.exists()) {
            f.delete();
        }
        System.out.println("........file eliminado exitoso " + rutaFinal);
    }

    private String obtenerRutaFinal(Map<String, String> parametros, String rutaBase) {
        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            rutaBase = rutaBase.replace(key, value);
        }
        return rutaBase;
    }

    private String obtenerExtension(String x) {
        return x.split(";")[0].split(":")[1].split("/")[1].toLowerCase();
    }

    private String obtenerTipo(String x) {
        return x.split(";")[0].split(":")[1].split("/")[0].toLowerCase();
    }
}
