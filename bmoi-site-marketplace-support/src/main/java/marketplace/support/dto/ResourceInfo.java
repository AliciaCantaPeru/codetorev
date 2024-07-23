/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class ResourceInfo {

    private String rutaBase;
    private String nombre;
    private String extension;
    private String rutaCompleta;

    public void setRutaCompleta(String rutaBaseRemota) {
        this.rutaCompleta = rutaBaseRemota.concat(rutaBase).concat(nombre).concat(".").concat(extension);
    }
}
