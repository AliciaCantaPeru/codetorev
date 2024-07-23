/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.categoriasgrupomenu;

import lombok.Data;

/**
 *
 * @author Martin Pilar
 */
@Data
public class CategoriasGrupomenuEntity {

    private int idCategoriaGrupoMenu;
    private int idCategoria;
    private String categoria;
    private String codigoCategoria;
    private int idGrupo;
    private String grupo;
    private String codigoMenu;

}
