/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.Personas;
import marketplace.repository.entity.personas.BmoiPersonaOpcionEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public interface PersonasRepositoryCustom {

    PageImpl<Personas> listarPersonasColaboradores(String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception;

    PageImpl<Personas> listarPersonasCuentasBmoi(String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception;

    List<BmoiPersonaOpcionEntity> listarOpcionPersonaCuentaBmoi(Integer idPersona) throws Exception;

    String obtenerNombreCompleto(String correo) throws Exception;

}
