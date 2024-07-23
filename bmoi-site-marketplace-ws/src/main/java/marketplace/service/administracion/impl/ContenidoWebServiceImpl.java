/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.google.common.collect.Lists;
import java.util.List;
import marketplace.repository.TblpageContentRepository;
import marketplace.repository.TblpageSlideRepository;
import marketplace.repository.entity.TblpageContent;
import marketplace.repository.entity.TblpageSlide;
import marketplace.service.administracion.ContenidoWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Pilar
 */
@Service
public class ContenidoWebServiceImpl implements ContenidoWebService {

    @Autowired
    private TblpageContentRepository tblpageContentRepository;
    @Autowired
    private TblpageSlideRepository tblpageSlideRepository;

    @Override
    public List<TblpageContent> listarPageContent() throws Exception {
        List<TblpageContent> content = Lists.newArrayList(tblpageContentRepository.findAll());
        return content;
    }

    @Override
    public List<TblpageSlide> listarPageSlide() throws Exception {
        List<TblpageSlide> content = Lists.newArrayList(tblpageSlideRepository.findAll());
        return content;
    }

}
