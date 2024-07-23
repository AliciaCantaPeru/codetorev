/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.usuarioseller;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class UsuarioMantDto {

    private Long id;
    private String email;
    private String contrasenia;
    private int estado;
    private int idSellerPersona;
    private int idPersona;
    private int idRol;
    private int idSeller;
    private int idPersonaLogeada;
}
