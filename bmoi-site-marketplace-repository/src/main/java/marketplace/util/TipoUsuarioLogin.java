/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Martin Pilar
 */
@Getter
@AllArgsConstructor
public enum TipoUsuarioLogin {
    BMOI("BMOI"),
    SELLER("SELLER");

    private final String valor;
}
