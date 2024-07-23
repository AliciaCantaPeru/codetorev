/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import com.querydsl.core.types.Predicate;
import java.util.List;
import marketplace.support.dto.Opcion;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface RolesRepositoryCustom {

    public List<Opcion> listarRolesPersona(Predicate p) throws Exception;
}
