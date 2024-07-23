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
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Getter
@AllArgsConstructor
public enum ResetTelemetria {
    ACTIVO(1),
    INACTIVO(0);

    private final int id;

    public static boolean getReset(int idReset) {
        switch (idReset) {
            case 1:
                return true;
            case 0:
                return false;
        }
        return false;
    }
}
