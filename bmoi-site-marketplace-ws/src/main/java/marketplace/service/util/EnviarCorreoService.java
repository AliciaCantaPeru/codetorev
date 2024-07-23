/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.util;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface EnviarCorreoService {

    void enviarCorreoRecuperarContrasenia(String correoDestino, String nombrePersona, String link) throws Exception;

    void enviarCorreoActivacionCuenta(String correoDestino, String nombrePersona, String passTemporal, String link) throws Exception;

}
