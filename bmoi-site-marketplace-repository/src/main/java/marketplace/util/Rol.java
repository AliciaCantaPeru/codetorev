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
public enum Rol {
    MASTER(1, "Master"),
    ADMIN(2, "ADMIN"),
    CONSULTOR(3, "CONSULTOR"),
    REPORTE(4, "REPORTE"),
    CLIENTE(5, "CLIENTE"),
    INVITADO(6, "INVITADO");

    private final int id;
    private final String descripcion;

    public static Rol getRol(int idRol) {
        switch (idRol) {
            case 1:
                return MASTER;
            case 2:
                return ADMIN;
            case 3:
                return CONSULTOR;
            case 4:
                return REPORTE;
            case 5:
                return CLIENTE;
            case 6:
                return INVITADO;
        }
        throw new ExceptionUser("Ud. no dispone de privilegos en el sistema", "No se encontro el rol " + idRol);
    }

    public static boolean esMaster(int idRol) {
        switch (idRol) {
            case 1:
                return true;
            case 2:
            case 3:
            case 4:
            case 5:
                return false;
        }
        throw new ExceptionUser("Ud. no dispone de privilegos en el sistema", "No se encontro el rol " + idRol);

    }

}
