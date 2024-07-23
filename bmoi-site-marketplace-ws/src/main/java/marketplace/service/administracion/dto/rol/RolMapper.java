/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.rol;

import java.util.List;
import org.mapstruct.Mapper;
import marketplace.repository.entity.Roles;

/**
 *
 * @author mpilar
 */
@Mapper(componentModel = "spring")
public interface RolMapper {

    List<RolMantDto> toRolMantDtos(Iterable<Roles> listaRoles);

    RolMantDto toRolMantDto(Roles roles);

    Roles toRoles(RolMantDto rolMantDto);

}
