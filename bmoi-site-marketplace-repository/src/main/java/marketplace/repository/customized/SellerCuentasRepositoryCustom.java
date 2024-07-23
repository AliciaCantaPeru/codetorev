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
public interface SellerCuentasRepositoryCustom {

    UsuarioEntity obtenerUsuario(String email, String tipoUsuario) throws Exception;

    String validaObtenerCorreo(int idUsuarioLogeado) throws Exception;

    String validaObtenerCorreo(int idUsuario, String tipoUsuario) throws Exception;

    PersonaCuentaEntity existeCuenta(String email, String tipoUsuario) throws Exception;

    boolean existeKey(String email, String keyNewPassword) throws Exception;

    void updateKeyNewPassword(int idPersona, String keyNewPassword) throws Exception;
}
