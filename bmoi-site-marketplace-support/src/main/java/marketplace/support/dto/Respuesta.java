/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import lombok.Data;

/**
 *
 * @author dvelasquez
 * @param <D>
 */
@Data
public class Respuesta<D> {

    private int tipo;
    private String mensaje;
    private String mensajeDev;
    private D dato;

    public Respuesta() {
    }

    public Respuesta(D dato) {
        this.dato = dato;
    }

    public Respuesta(int tipo, String mensaje, D dato) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.dato = dato;
    }
}
