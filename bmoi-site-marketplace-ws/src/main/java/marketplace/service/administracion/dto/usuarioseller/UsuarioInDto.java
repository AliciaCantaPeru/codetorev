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
public class UsuarioInDto {

    private int idPersonaLogeada;
    private String texto;
    private int pagina;
    private int cantidad;
    private String sortCampo;
    private String sortOrden;

}
