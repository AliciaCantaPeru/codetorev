/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.autenticacion;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class AutLoginInDto {

    private String correo;
    private String contrasenia;
    private String tipoUsuario;

}
