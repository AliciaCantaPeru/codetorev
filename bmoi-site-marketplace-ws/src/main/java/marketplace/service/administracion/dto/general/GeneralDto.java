/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.general;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Martin Pilar
 */
@Data
public class GeneralDto {

    private List<ParametroDto> listaParametro;
    private List<OpcionDto> listaOpciones;

}
