/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.figimgdiseniosubcategorias;

import lombok.Data;

/**
 *
 * @author Martin Pilar
 */
@Data
public class GrupoCategoriaDisenioEntity {

    private int idSubcategoria;
    private String subcategoria;
    private int idCategoria;
    private String categoria;
    private int idGrupo;
    private String grupo;

}
