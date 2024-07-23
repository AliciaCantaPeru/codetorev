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
public enum EstadoDispositivo {
    DISPONIBLE("Disponible"),
    VENDIDO("Vendido"),
    ALQUILADO("Alquilado"),
    REPARACION("En reparación"),
    PRUEBA("En prueba");

    private final String valor;
}
