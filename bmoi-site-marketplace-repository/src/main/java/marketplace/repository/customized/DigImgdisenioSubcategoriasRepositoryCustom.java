/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.figimgdiseniosubcategorias.GrupoCategoriaDisenioEntity;

/**
 *
 * @author Martin Pilar
 */
public interface DigImgdisenioSubcategoriasRepositoryCustom {

    List<GrupoCategoriaDisenioEntity> listarGrupoCategoriaDisenio(int idGrupo) throws Exception;

    List<GrupoCategoriaDisenioEntity> listarGrupoCategoriaDisenioProducto(int idProducto) throws Exception;
}
