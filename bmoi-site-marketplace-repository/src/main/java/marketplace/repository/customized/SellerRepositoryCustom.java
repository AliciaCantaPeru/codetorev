/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.sellers.SellerEntity;
import marketplace.support.dto.Opcion;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public interface SellerRepositoryCustom {

    PageImpl<SellerEntity> listarSeller(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception;

    Integer obtenerIdSeller(int idSellerPersona) throws Exception;

    List<Opcion> listarOpcionSeller(int idSeller) throws Exception;
}
