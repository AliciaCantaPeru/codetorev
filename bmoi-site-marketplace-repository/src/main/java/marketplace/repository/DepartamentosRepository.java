/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import marketplace.repository.entity.Departamentos;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Repository
public interface DepartamentosRepository extends CrudRepository<Departamentos, Integer>, QuerydslPredicateExecutor<Departamentos> {

}
