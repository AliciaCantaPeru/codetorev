/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import marketplace.logger.ExceptionUser;

/**
 *
 * @author mpilar
 */
@Getter
@AllArgsConstructor
public enum TipoInterfaz {
    SYS(1, "SYS"),
    WEB(2, "WEB"),
    APP(3, "APP"),;

    private final int id;
    private final String descripcion;

    public static TipoInterfaz getRol(int idRol) {
        switch (idRol) {
            case 1:
                return WEB;
            case 2:
                return APP;
        }
        throw new ExceptionUser("Ud. no dispone de privilegos en el sistema", "No se encontro el rol " + idRol);
    }
}
