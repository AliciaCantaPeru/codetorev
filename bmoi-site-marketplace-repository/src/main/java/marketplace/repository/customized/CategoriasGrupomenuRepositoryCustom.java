/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.util.List;
import marketplace.repository.entity.categoriasgrupomenu.CategoriasGrupomenuEntity;

/**
 *
 * @author Martin Pilar
 */
public interface CategoriasGrupomenuRepositoryCustom {

    List<CategoriasGrupomenuEntity> listarGrupoCategoria(String codigoMenu) throws Exception;
}
