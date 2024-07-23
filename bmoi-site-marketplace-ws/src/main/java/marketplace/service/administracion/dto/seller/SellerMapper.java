/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.seller;

import java.util.Date;
import java.util.List;
import marketplace.repository.entity.Direcciones;
import marketplace.repository.entity.Personas;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.SellerRedes;
import marketplace.repository.entity.Sellers;
import marketplace.repository.entity.sellers.SellerEntity;
import marketplace.util.EstadoRegistro;
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
public interface SellerMapper {

    List<SellerOutDto> toSellerOutDtos(List<SellerEntity> sellerEntity);

    SellerMantDto.PersonasDto toPersonasDto(SellerPersonas persona);

    @Mappings({
        @Mapping(target = "url", source = "redesSociales.red")})
    SellerMantDto.RedesSocialesDto toRedesSocialesDto(SellerRedes redesSociales);

    @Mappings({
        @Mapping(target = "idDistrito", source = "idDistrito.id"),
        @Mapping(target = "idPais", source = "idDepartamento.idpais.id"),
        @Mapping(target = "idProvincia", source = "idProvincia.id"),
        @Mapping(target = "idDepartamento", source = "idDepartamento.id")})
    SellerMantDto.DireccionDto toDireccionDto(Direcciones direccion);

    @Mappings({
        @Mapping(target = "idpostulante", source = "idpostulante.id")
    })
    SellerMantDto.SellerEmpresaDto toSellerDto(Sellers sellers);

    List<SellerMantDto.PersonasDto> toPersonasDtos(List<Personas> persona);

    @Mappings({
        @Mapping(target = "id", source = "redesSocialesDto.id"),
        @Mapping(target = "nombre", source = "redesSocialesDto.nombre"),
        @Mapping(target = "red", source = "redesSocialesDto.url"),
        @Mapping(target = "idSeller.id", source = "idSeller")})
    SellerRedes toRedesSociale(SellerMantDto.RedesSocialesDto redesSocialesDto, Integer idSeller);

    @Mappings({
        @Mapping(target = "idDistrito.id", source = "direccionDto.idDistrito"),
        @Mapping(target = "idPais.id", source = "direccionDto.idPais"),
        @Mapping(target = "idProvincia.id", source = "direccionDto.idProvincia"),
        @Mapping(target = "idDepartamento.id", source = "direccionDto.idDepartamento")})
    Direcciones toDireccion(SellerMantDto.DireccionDto direccionDto);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Sellers target, SellerMantDto.SellerEmpresaDto source, String usuario) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
            target.setUsuRegistro(usuario);
        } else {
            target.setFecActualizacion(new Date());
            target.setUsuActualizacion(usuario);
        }
        target.setEstado(EstadoRegistro.ACTIVO.getId());
    }

    @Mappings({
        @Mapping(target = "id", source = "empresaDto.id"),
        @Mapping(target = "iddireccion.id", source = "idDireccion"),
        @Mapping(target = "idpostulante.id", source = "empresaDto.idpostulante")
    })
    Sellers toSeller(SellerMantDto.SellerEmpresaDto empresaDto, Integer idDireccion, String usuario);

    @BeforeMapping
    default void beforeMappingToPerson(@MappingTarget SellerPersonas target, SellerMantDto.PersonasDto source, String usuario, Integer idSeller) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
            target.setUsuRegistro(usuario);
        } else {
            target.setFecActualizacion(new Date());
            target.setUsuActualizacion(usuario);
        }
        target.setCntPrincipal(1);
        target.setEstado(EstadoRegistro.ACTIVO.getId());
    }

    @Mappings({
        @Mapping(target = "id", source = "personasDtos.id"),
        @Mapping(target = "email", source = "personasDtos.email"),
        @Mapping(target = "idseller.id", source = "idSeller"),
        @Mapping(target = "cntPrincipal", ignore = true)
    })
    SellerPersonas toPersonas(SellerMantDto.PersonasDto personasDtos, String usuario, Integer idSeller);

}
