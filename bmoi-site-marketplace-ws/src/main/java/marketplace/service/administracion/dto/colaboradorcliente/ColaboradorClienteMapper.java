/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorcliente;

import java.util.List;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import marketplace.repository.entity.Personas;

/**
 *
 * @author mpilar
 */
@Mapper(componentModel = "spring")
public interface ColaboradorClienteMapper {

    List<ColaboradorClienteOutDto> toColaboradorOutDto(List<Personas> entity);

    @BeforeMapping
    default void beforeMappingToPerson(@MappingTarget Personas target, ColaboradorClienteMantDto source, String usuario) {
//        if (source.getId() == null) {
//            target.setFecRegistro(new Date());
//            target.setUsuRegistro(usuario);
//        } else {
//            target.setFecActualizacion(new Date());
//            target.setUsuActualizacion(usuario);
//        }
//        target.setEstado(EstadoRegistro.ACTIVO.getId());
//        target.setIdcliente(new Clientes(source.getIdCliente()));
    }

    Personas toPersonas(ColaboradorClienteMantDto dto, String usuario);

    ColaboradorClienteMantDto toColaboradorMantDto(Personas dto);
}
