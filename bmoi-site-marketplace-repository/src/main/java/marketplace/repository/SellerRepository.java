/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.customized.SellerRepositoryCustom;
import marketplace.repository.entity.Sellers;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */

@Repository
public interface SellerRepository extends CrudRepository<Sellers, Integer>, QuerydslPredicateExecutor<Sellers>, SellerRepositoryCustom {

}
