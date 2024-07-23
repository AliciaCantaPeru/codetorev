/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.cuentas;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class UsuarioEntity implements Serializable {

    private Integer idPersona;
    private String email;
    private String contrasenia;
    private String rol;

}
