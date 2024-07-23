/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import marketplace.service.administracion.dto.usuarioseller.OpcionPersona;
import marketplace.service.administracion.dto.usuarioseller.UsuarioInDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioMantDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioOutDto;
import marketplace.support.dto.Opcion;

/**
 *
 * @author mpilar
 */
public interface UsuarioBmoiService extends GenericService<UsuarioInDto, UsuarioOutDto, UsuarioMantDto> {

    List<Opcion> listarOpcionSeller(int idPersonaLogeada, int idSeller) throws Exception;

    List<OpcionPersona> listarOpcionPersonas(int idPersonaLogeada, int idSeller, Integer idPersonaSeller) throws Exception;

    List<Opcion> listarOpcionRoles(int idPersonaLogeada) throws Exception;

}
