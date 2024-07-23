/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support;

import marketplace.support.dto.Respuesta;

/**
 *
 * @author jvelasquez
 */
public interface MensajeSupport {

    <T> Respuesta<T> respuestaEliminar(T dato);

    <T> Respuesta<T> respuestaAgregar(T dato);

    <T> Respuesta<T> respuestaActualizar(T dato);

    <T> Respuesta<T> respuestaListar(T dato);

    <T> Respuesta<T> respuestaObtener(T dato);

    <T> Respuesta<T> respuestaCorrecta(T dato, String mensaje);

}
