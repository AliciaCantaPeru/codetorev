/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support;

import marketplace.support.dto.CorreoInfo;

/**
 *
 * @author mpilar
 */
public interface EnviarCorreoSupport {

    void enviarCorreo(CorreoInfo correoInfo) throws Exception;

}
