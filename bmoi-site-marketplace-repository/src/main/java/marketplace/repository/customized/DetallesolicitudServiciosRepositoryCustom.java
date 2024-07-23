/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface DetallesolicitudServiciosRepositoryCustom {

    List<Integer> listarIdTipoServicio(int idSolicitud) throws Exception;

}
