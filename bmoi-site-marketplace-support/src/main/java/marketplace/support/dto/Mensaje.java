/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author jvelasquez
 */
@Data
@AllArgsConstructor
public class Mensaje implements Serializable {

    private String codigo;
    private String mensaje;
    private String mensajeDev;
    private int tipo;

    public Mensaje() {
    }

}
