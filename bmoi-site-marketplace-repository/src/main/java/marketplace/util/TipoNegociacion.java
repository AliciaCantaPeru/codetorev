/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Getter
@AllArgsConstructor
public enum TipoNegociacion {
    PERIODO_PRUEBA("Periodo de prueba"),
    ORDEN_ALQUILER("Orden de Alquiler"),
    ORDEN_COMPRA("Orden de Compra");

    private final String valor;

    public static List<String> listarNegociacion() {
        return Arrays.asList(PERIODO_PRUEBA.valor, ORDEN_ALQUILER.valor, ORDEN_COMPRA.valor);
    }
}
