/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.empresa;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class EmpresaInDto {

    private int idPersonaLogeada;
    private boolean negocio;
    private String texto;
    private int pagina;
    private int cantidad;
    private String sortCampo;
    private String sortOrden;
}
