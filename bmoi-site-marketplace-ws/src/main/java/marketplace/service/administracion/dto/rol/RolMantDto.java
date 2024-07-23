/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.rol;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class RolMantDto {

    private int id;
    private String nombre;
    private String descripcion;
    private String estado;

}
