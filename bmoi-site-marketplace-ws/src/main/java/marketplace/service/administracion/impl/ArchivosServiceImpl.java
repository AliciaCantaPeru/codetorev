/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import java.util.ArrayList;
import java.util.List;
import marketplace.service.administracion.ArchivosService;
import marketplace.service.administracion.dto.archivo.ArchivoDto;
import marketplace.service.util.AmazonClientService;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar
 */
@Service
public class ArchivosServiceImpl implements ArchivosService {

    @Autowired
    private AmazonClientService amazonClientService;

    @Override
    public List<ArchivoDto> GuardarFile(MultipartFile[] files, int idSeller) throws Exception {
        List<ArchivoDto> listaFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = amazonClientService.uploadFile(file, "recursos/seller/" + idSeller + "/" + Util.getFechaString());
            listaFiles.add(new ArchivoDto(url));
        }
        return listaFiles;
    }

}
