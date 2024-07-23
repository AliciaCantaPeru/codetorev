/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorbmoi;

import java.util.List;
import marketplace.repository.entity.Personas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author mpilar
 */
@Mapper(componentModel = "spring")
public interface ColaboradorBmoiMapper {

    List<ColaboradorBmoiOutDto> toColaboradorOutDto(List<Personas> entitys);

    @Mappings({
        @Mapping(target = "rol", source = "entitys.cuentas.idRol.nombre")})
    ColaboradorBmoiOutDto toColaboradorOutDto(Personas entitys);

    Personas toPersonas(ColaboradorBmoiMantDto dto, String usuario);

    ColaboradorBmoiMantDto toColaboradorMantDto(Personas sellerPersonas);
}
