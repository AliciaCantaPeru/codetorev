/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.Productos;
import marketplace.repository.entity.producto.ProductoSolicitudOptionEntity;
import marketplace.repository.entity.productos.ProductoOptionEntity;
import marketplace.support.dto.Opcion;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface ProductosRepositoryCustom {

    PageImpl<Productos> listarProducto(String sortCampo, String sortOrden, String seach, PageRequest pageable, Integer idSeller, int seccion) throws Exception;

    List<ProductoOptionEntity> listarProductoEntity() throws Exception;

    List<ProductoSolicitudOptionEntity> listarProductoOptionSolicitud(Integer idProducto) throws Exception;

    List<Opcion> listarProductoOpcion(Integer idSeller) throws Exception;

    String ObtenerUltimoSku(int idSeller) throws Exception;

}
