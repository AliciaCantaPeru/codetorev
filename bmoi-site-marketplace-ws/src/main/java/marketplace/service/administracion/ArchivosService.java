/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import marketplace.service.administracion.dto.archivo.ArchivoDto;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar
 */
public interface ArchivosService {

    List<ArchivoDto> GuardarFile(MultipartFile[] files, int idSeller) throws Exception;
}
