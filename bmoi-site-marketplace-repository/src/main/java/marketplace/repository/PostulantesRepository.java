/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository;

import marketplace.repository.entity.Postulantes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Pilar
 */
@Repository
public interface PostulantesRepository extends CrudRepository<Postulantes, Integer> {

}
