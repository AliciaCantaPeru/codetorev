/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import marketplace.support.dto.TipoRespuesta;

@Log4j2
@Service
public class MensajeSupportImpl implements MensajeSupport {

    public static final String TIPO_ELIMINAR_CORRECTO = "El registro fue eliminado con éxito";
    public static final String TIPO_AGREGAR_CORRECTO = "El registro se guardo con éxito";
    public static final String TIPO_ACTUALIZAR_CORRECTO = "El registro se actualizo con éxito";
    public static final String TIPO_LISTAR_CORRECTO = "Consulta de registros exitoso";
    public static final String TIPO_OBTENER_CORRECTO = "Consulta de registro exitoso";

    @Override
    public <T> Respuesta<T> respuestaEliminar(T dato) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, TIPO_ELIMINAR_CORRECTO, dato);
    }

    @Override
    public <T> Respuesta<T> respuestaAgregar(T dato) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, TIPO_AGREGAR_CORRECTO, dato);
    }

    @Override
    public <T> Respuesta<T> respuestaActualizar(T dato) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, TIPO_ACTUALIZAR_CORRECTO, dato);
    }

    @Override
    public <T> Respuesta<T> respuestaListar(T dato) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, TIPO_LISTAR_CORRECTO, dato);
    }

    @Override
    public <T> Respuesta<T> respuestaObtener(T dato) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, TIPO_OBTENER_CORRECTO, dato);
    }

    @Override
    public <T> Respuesta<T> respuestaCorrecta(T dato, String mensaje) {
        return new Respuesta<>(TipoRespuesta.TIPO_CORRECTO, mensaje, dato);
    }

}
