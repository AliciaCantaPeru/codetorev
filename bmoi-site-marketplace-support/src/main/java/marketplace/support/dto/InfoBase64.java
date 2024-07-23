/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
@AllArgsConstructor
public class InfoBase64 {

    private byte[] base64;
    private String extension;
    private String tipo;
}
