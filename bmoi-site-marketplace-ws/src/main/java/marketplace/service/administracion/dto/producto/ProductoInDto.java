/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.producto;

import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ProductoInDto {

    private int idPersonaLogeada;
    private boolean negocio;
    private String texto;
    private int pagina;
    private int cantidad;
    private int seccion;
    private String sortCampo;
    private String sortOrden;
}
