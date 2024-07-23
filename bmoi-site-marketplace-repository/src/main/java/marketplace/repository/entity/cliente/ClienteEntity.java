/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.cliente;

import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class ClienteEntity {

    private String razSocial;
    private String rubro;
    private String nombre;
    private String ruc;
    private String telefono;
    private String email;
}
