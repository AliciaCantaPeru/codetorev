/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import marketplace.repository.entity.cuentas.PersonaCuentaEntity;
import marketplace.repository.entity.cuentas.UsuarioEntity;

/**
 *
 * @author mpilar
 */
public interface CuentasRepositoryCustom {

    UsuarioEntity obtenerUsuario(String email) throws Exception;

    String validaObtenerCorreo(int idUsuario) throws Exception;

    PersonaCuentaEntity existeCuenta(String email) throws Exception;

    boolean existeKey(String email, String keyNewPassword) throws Exception;

    void updateKeyNewPassword(int idPersona, String keyNewPassword) throws Exception;
}
