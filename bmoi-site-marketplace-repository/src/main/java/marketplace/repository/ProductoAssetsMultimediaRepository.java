/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import java.util.List;
import javax.transaction.Transactional;
import marketplace.repository.entity.ProductoAssetsMultimedia;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */
@Repository
public interface ProductoAssetsMultimediaRepository extends CrudRepository<ProductoAssetsMultimedia, Integer>, QuerydslPredicateExecutor<ProductoAssetsMultimedia> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM ProductoAssetsMultimedia x WHERE x.id in ?1")
    @Transactional
    void delete(List<Integer> id);
}
