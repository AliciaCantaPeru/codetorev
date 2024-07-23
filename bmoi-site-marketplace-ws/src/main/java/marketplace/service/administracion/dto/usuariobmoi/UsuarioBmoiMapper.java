/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.usuariobmoi;

import java.util.Date;
import java.util.List;
import marketplace.repository.entity.Cuentas;
import marketplace.repository.entity.Personas;
import marketplace.repository.entity.personas.BmoiPersonaOpcionEntity;
import marketplace.repository.entity.personas.SellerPersonaOpcionEntity;
import marketplace.util.Encriptador;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 *
 * @author mpilar
 */
@Mapper(componentModel = "spring")
public interface UsuarioBmoiMapper {

    Encriptador e = new Encriptador();

    List<UsuarioBmoiOutDto> toUsuarioOutDtos(List<Personas> clienteEntity);

    @Mappings({
        @Mapping(target = "contrasenia", ignore = true),
        @Mapping(target = "idPersona", source = "cuentas.idPersona.id"),
        @Mapping(target = "idRol", source = "cuentas.idRol.id"),
        @Mapping(target = "email", source = "cuentas.idPersona.email")
    })
    UsuarioBmoiMantDto toMantenimientoDto(Cuentas cuentas);

    @BeforeMapping
    default void beforeMapping(@MappingTarget UsuarioBmoiOutDto target, Personas source) {
        target.setRol(source.getCuentas() != null ? source.getCuentas().getIdRol().getNombre() : "");
    }

    UsuarioBmoiOutDto toUsuarioOutDto(Personas cuentas);

    OpcionPersonaBmoi toOpcionPersona(SellerPersonaOpcionEntity opcionEntity);

    List<OpcionPersonaBmoi> toOpcionPersonas(List<BmoiPersonaOpcionEntity> clienteEntity);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Cuentas target, UsuarioBmoiMantDto source) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
        }
        String pass = e.encriptarTexto(source.getContrasenia());
        target.setContrasenia(pass);
    }

    @Mappings({
        @Mapping(target = "contrasenia", ignore = true),
        @Mapping(target = "fecRegistro", ignore = true),
        @Mapping(target = "idPersona.id", source = "usuarioMantenimientoDto.idPersona"),
        @Mapping(target = "idRol.id", source = "usuarioMantenimientoDto.idRol")
    })
    Cuentas toCuentas(UsuarioBmoiMantDto usuarioMantenimientoDto);
}
