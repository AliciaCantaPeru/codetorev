/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.customized.SellerPersonasRepositoryCustom;
import marketplace.repository.entity.SellerPersonas;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */
@Repository
public interface SellerPersonasRepository extends CrudRepository<SellerPersonas, Integer>, QuerydslPredicateExecutor<SellerPersonas>, SellerPersonasRepositoryCustom {

}
