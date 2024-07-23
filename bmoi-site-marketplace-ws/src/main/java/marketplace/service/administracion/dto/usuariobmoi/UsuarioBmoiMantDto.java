/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.usuariobmoi;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class UsuarioBmoiMantDto {

    private Integer id;
    private String email;
    private String contrasenia;
    private int estado;
    private int idPersona;
    private int idRol;
    private int idPersonaLogeada;
}
