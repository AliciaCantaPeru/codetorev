/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.customized.ConfiguracionesRespositoryCustom;
import marketplace.repository.entity.TblmasterConfiguraciones;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Repository
public interface ConfiguracionesRepository extends CrudRepository<TblmasterConfiguraciones, Integer>, QuerydslPredicateExecutor<TblmasterConfiguraciones>, ConfiguracionesRespositoryCustom {

}
