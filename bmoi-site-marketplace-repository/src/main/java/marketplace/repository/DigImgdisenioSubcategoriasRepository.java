/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.customized.DigImgdisenioSubcategoriasRepositoryCustom;
import marketplace.repository.entity.DigImgdisenioSubcategorias;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */
@Repository
public interface DigImgdisenioSubcategoriasRepository extends CrudRepository<DigImgdisenioSubcategorias, Integer>, QuerydslPredicateExecutor<DigImgdisenioSubcategorias>, DigImgdisenioSubcategoriasRepositoryCustom {

}
