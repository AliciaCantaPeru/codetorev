/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import marketplace.repository.entity.Clientes;
import marketplace.repository.entity.cliente.ClienteOpcionSolicitudEntity;
import marketplace.support.dto.Opcion;

/**
 *
 * @author mpilar
 */
public interface ClientesRepositoryCustom {

    PageImpl<Clientes> listarCliente(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception;

    Integer obtenerIdCliente(int idPersona) throws Exception;

    List<Opcion> listarOpcionCliente(int idCliente) throws Exception;

    List<ClienteOpcionSolicitudEntity> listarOpcionClienteSolicitud() throws Exception;

}
