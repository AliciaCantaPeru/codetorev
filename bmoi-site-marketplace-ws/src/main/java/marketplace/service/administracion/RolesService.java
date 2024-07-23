/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import marketplace.service.administracion.dto.rol.RolMantDto;

/**
 *
 * @author mpilar
 */
public interface RolesService {

    List<RolMantDto> listar(Integer idPersonaLogeada) throws Exception;

    void actualizar(Integer idPersonaLogeada, RolMantDto mantenimientoDto) throws Exception;

}
