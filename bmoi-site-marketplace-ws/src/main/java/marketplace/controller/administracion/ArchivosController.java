/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.util.List;
import marketplace.service.administracion.ArchivosService;
import marketplace.service.administracion.dto.archivo.ArchivoDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar
 */
@RestController
@RequestMapping("api/archivo")
public class ArchivosController {

    @Autowired
    private MensajeSupport mensajeSupport;
    @Autowired
    private ArchivosService archivosService;

    @PostMapping()
    public Respuesta<List<ArchivoDto>> guardar(@RequestPart(name = "files", required = false) MultipartFile[] files, @RequestPart("idSeller") String idSeller) throws Exception {
        List<ArchivoDto> dto = archivosService.GuardarFile(files, Integer.parseInt(idSeller));
        return mensajeSupport.respuestaAgregar(dto);
    }

}
