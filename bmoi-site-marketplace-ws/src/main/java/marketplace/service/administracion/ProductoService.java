/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import marketplace.service.administracion.dto.producto.ProductoInDto;
import marketplace.service.administracion.dto.producto.ProductoMantDto;
import marketplace.service.administracion.dto.producto.ProductoOutDto;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface ProductoService extends GenericService<ProductoInDto, ProductoOutDto, ProductoMantDto> {

    void ver(int idPersonaLogeada, int idProducto) throws Exception;

    void destacar(int idPersonaLogeada, int idProducto) throws Exception;

}
