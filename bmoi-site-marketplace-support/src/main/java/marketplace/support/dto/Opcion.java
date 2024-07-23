/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class Opcion {

    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String img;

}
