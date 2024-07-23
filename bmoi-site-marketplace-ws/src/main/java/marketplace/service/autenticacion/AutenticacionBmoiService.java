/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.autenticacion;

import marketplace.service.administracion.dto.autenticacion.AutUsuarioOutDto;
import marketplace.service.administracion.dto.autenticacion.CambiarContraseniaInDto;
import marketplace.service.administracion.dto.autenticacion.KeyInDto;

/**
 *
 * @author PCADMIN
 */
public interface AutenticacionBmoiService {

    AutUsuarioOutDto obtenerUsuario(String idUsuario) throws Exception;

    void cambiarContraseniaEnviarLink(String correo) throws Exception;

    String cambiarContraseniaVerficarKey(KeyInDto keyInDto) throws Exception;

    void cambiarContrasenia(CambiarContraseniaInDto cambiarContraseniaInDto) throws Exception;

    void activarCuenta(CambiarContraseniaInDto cambiarContraseniaInDto) throws Exception;
}
