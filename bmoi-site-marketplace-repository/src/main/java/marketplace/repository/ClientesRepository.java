package marketplace.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import marketplace.repository.customized.ClientesRepositoryCustom;
import marketplace.repository.entity.Clientes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mpilar
 */
@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Integer>, QuerydslPredicateExecutor<Clientes>, ClientesRepositoryCustom {

}
