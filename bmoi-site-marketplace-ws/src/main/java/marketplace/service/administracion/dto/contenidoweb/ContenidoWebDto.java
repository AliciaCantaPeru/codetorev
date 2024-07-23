/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.contenidoweb;

import java.util.List;
import lombok.Data;
import marketplace.repository.entity.TblpageContent;
import marketplace.repository.entity.TblpageSlide;

/**
 *
 * @author Martin Pilar
 */
@Data
public class ContenidoWebDto {

    private List<TblpageContent> listaContent;
    private List<TblpageSlide> listaSlide;
}
