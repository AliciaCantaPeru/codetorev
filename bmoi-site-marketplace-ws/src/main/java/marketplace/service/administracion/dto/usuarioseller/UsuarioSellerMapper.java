/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.usuarioseller;

import java.util.Date;
import java.util.List;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.SellerPersonas;
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
public interface UsuarioSellerMapper {

    Encriptador e = new Encriptador();

    List<UsuarioOutDto> toUsuarioOutDtos(List<SellerPersonas> clienteEntity);

    @Mappings({
        @Mapping(target = "contrasenia", ignore = true),
        @Mapping(target = "idSeller", source = "cuentas.idSellerpersona.idseller.id"),
        @Mapping(target = "idSellerPersona", source = "cuentas.idSellerpersona.id"),
        @Mapping(target = "idRol", source = "cuentas.idRol.id"),
        @Mapping(target = "email", source = "cuentas.idSellerpersona.email")
    })
    UsuarioMantDto toMantenimientoDto(SellerCuentas cuentas, Integer idCliente);

    @BeforeMapping
    default void beforeMapping(@MappingTarget UsuarioOutDto target, SellerPersonas source) {
        target.setRol(source.getSellerCuentas() != null ? source.getSellerCuentas().getIdRol().getNombre() : "");
        target.setCliente(source.getIdseller() != null ? source.getIdseller().getNomComercial() : "");
    }

    UsuarioOutDto toUsuarioOutDto(SellerPersonas cuentas);

    OpcionPersona toOpcionPersona(SellerPersonaOpcionEntity opcionEntity);

    List<OpcionPersona> toOpcionPersonas(List<SellerPersonaOpcionEntity> clienteEntity);

    @BeforeMapping
    default void beforeMapping(@MappingTarget SellerCuentas target, UsuarioMantDto source) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
        }
        String pass = e.encriptarTexto(source.getContrasenia());
        target.setContrasenia(pass);
    }

    @Mappings({
        @Mapping(target = "contrasenia", ignore = true),
        @Mapping(target = "fecRegistro", ignore = true),
        @Mapping(target = "idSellerpersona.id", source = "usuarioMantenimientoDto.idSellerPersona"),
        @Mapping(target = "idRol.id", source = "usuarioMantenimientoDto.idRol")
    })
    SellerCuentas toCuentas(UsuarioMantDto usuarioMantenimientoDto);
}
