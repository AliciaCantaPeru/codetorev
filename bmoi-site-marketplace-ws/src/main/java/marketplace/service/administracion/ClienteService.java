/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import marketplace.service.administracion.dto.empresa.EmpresaInDto;
import marketplace.service.administracion.dto.empresa.EmpresaMantDto;
import marketplace.service.administracion.dto.empresa.EmpresaOutDto;
import org.springframework.stereotype.Service;

/**
 *
 * @author mpilar
 */
@Service
public interface ClienteService extends GenericSecondaryService<EmpresaInDto, EmpresaOutDto, EmpresaMantDto> {

}
