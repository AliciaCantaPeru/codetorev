/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.entity.DigImgdisenioSubcategoriasImg;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */
@Repository
public interface DigImgdisenioSubcategoriasImgRepository extends CrudRepository<DigImgdisenioSubcategoriasImg, Integer>, QuerydslPredicateExecutor<DigImgdisenioSubcategoriasImg> {

}
