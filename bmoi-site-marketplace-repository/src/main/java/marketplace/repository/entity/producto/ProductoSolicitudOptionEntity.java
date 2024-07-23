/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.producto;

import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ProductoSolicitudOptionEntity {

    private Integer id;
    private String nombre;
    private Double prcVenta;
    private int stock;
}
