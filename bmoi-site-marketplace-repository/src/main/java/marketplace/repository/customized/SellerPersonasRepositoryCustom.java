/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.personas.SellerPersonaOpcionEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public interface SellerPersonasRepositoryCustom {

    PageImpl<SellerPersonas> listarSellerPersonas(String tipoUsuario, String sortCampo, String sortOrden, String seach, Integer idSeller, PageRequest pageable, boolean esCuenta) throws Exception;

    PageImpl<SellerPersonas> listarSellerPersonasColaboradores(String tipoUsuario, String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception;

    List<SellerPersonaOpcionEntity> listarOpcionPersonaSellerCuenta(String tipoUsuario, int idSeller, Integer idSellerPersona) throws Exception;

    String obtenerNombreCompleto(String tipoUsuario, String correo) throws Exception;

    String obtenerNombreCompletoPersonaPrincipal(String tipoUsuario, int idSeller) throws Exception;
}
