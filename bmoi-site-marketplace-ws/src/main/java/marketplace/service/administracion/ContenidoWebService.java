/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import marketplace.repository.entity.TblpageContent;
import marketplace.repository.entity.TblpageSlide;

/**
 *
 * @author Martin Pilar
 */
public interface ContenidoWebService {

    List<TblpageContent> listarPageContent() throws Exception;

    List<TblpageSlide> listarPageSlide() throws Exception;

}
