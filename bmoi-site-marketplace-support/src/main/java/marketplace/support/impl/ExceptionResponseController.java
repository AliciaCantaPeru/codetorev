/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import marketplace.logger.ExceptionUser;
import marketplace.support.dto.Respuesta;
import marketplace.support.dto.TipoRespuesta;

/**
 *
 * @author Martin Pilar <mpilarcastillejo@gmail.com>
 */
@RestControllerAdvice
@EnableWebMvc
public class ExceptionResponseController {

    private final static String MENSAJE_ERROR_NO_CONTROLADO = "Lo sentimos, no se pudo realizar su petición, inténtelo en otro momento.";

    @ExceptionHandler(value = {NoHandlerFoundException.class, Exception.class, RuntimeException.class, ExceptionUser.class})
    @ResponseBody
    public ResponseEntity<Respuesta> manejoErroresRest(Exception e, WebRequest request) {
        String mensaje;
        String mensajeDev = null;
        int tipoRespuesta = TipoRespuesta.TIPO_ERROR;
        if (e instanceof ExceptionUser) {
            mensaje = e.getMessage();
            mensajeDev = ((ExceptionUser) e).getMensajeDev();
            tipoRespuesta = TipoRespuesta.TIPO_ALERTA;
        } else if (e instanceof NoHandlerFoundException) {
            mensaje = "El recurso solicitado no existe" + ((NoHandlerFoundException) e).getRequestURL();
        } else {
            mensaje = MENSAJE_ERROR_NO_CONTROLADO;
        }
        String header = request.getHeader("Accept-Language");
        Respuesta respuesta = new Respuesta();
        respuesta.setTipo(tipoRespuesta);
        respuesta.setMensaje(mensaje);
        respuesta.setMensajeDev(mensajeDev != null ? mensajeDev : e.getMessage());
        return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
    }

}
