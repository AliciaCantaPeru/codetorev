/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.unidadnegocio;

import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class UnidadNegocioProductoEntity {

    private Integer idUnidadNegocio;
    private String nombreUnidadNegocio;
    private Integer idProducto;
    private String nombreProducto;
}
