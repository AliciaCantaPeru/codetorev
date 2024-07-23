/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.customized.PersonasRepositoryCustom;
import marketplace.repository.entity.Personas;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mpilar
 */
@Repository
public interface PersonasRepository extends CrudRepository<Personas, Integer>, QuerydslPredicateExecutor<Personas>, PersonasRepositoryCustom {

}
