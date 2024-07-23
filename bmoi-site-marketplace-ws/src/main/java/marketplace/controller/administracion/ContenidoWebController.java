/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import marketplace.service.administracion.ContenidoWebService;
import marketplace.service.administracion.dto.contenidoweb.ContenidoWebDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Pilar
 */
@RestController
@RequestMapping("api/contenido-web")
public class ContenidoWebController {

    @Autowired
    private MensajeSupport mensajeSupport;
    @Autowired
    private ContenidoWebService contenidoWebService;

    @GetMapping()
    public Respuesta<ContenidoWebDto> listarParametros() throws Exception {
        ContenidoWebDto contenidoWebDto = new ContenidoWebDto();
        contenidoWebDto.setListaContent(contenidoWebService.listarPageContent());
        contenidoWebDto.setListaSlide(contenidoWebService.listarPageSlide());
        return mensajeSupport.respuestaListar(contenidoWebDto);
    }
}
