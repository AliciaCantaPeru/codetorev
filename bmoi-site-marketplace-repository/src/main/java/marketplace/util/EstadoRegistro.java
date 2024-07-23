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
public enum EstadoRegistro {
    ACTIVO(1, "Activo"),
    INACTIVO(0, "Inactivo");

    private final int id;
    private final String descripcion;

    public static EstadoRegistro getEstadoRegistro(int idEstadoRegistro) {
        switch (idEstadoRegistro) {
            case 1:
                return ACTIVO;
            case 2:
                return INACTIVO;
        }
        throw new ExceptionUser("Ud. no dispone de privilegos en el sistema", "No se encontro el rol " + idEstadoRegistro);
    }
}
