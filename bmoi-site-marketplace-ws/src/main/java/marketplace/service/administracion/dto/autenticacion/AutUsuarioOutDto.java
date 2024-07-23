/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.autenticacion;

import lombok.Data;

/**
 *
 * @author PCADMIN
 */
@Data
public class AutUsuarioOutDto {

    private Integer idPersona;
    private Integer idSeller;
    private Integer idSellerPersona;
    private String correo;
    private String rol;
    private String nombres;
    private String empresa;
    private String plan;
    private String tipoUsuario;
    private String temaApp;
    private int idRol;

}
