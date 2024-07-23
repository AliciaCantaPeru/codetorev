/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorseller;

import java.util.Date;
import java.util.List;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.Sellers;
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
public interface ColaboradorSellerMapper {

    List<ColaboradorSellerOutDto> toColaboradorOutDto(List<SellerPersonas> entitys);

    @BeforeMapping
    default void beforeMappingToPerson(@MappingTarget SellerPersonas target, ColaboradorSellerMantDto source, String usuario) {
        if (source.getId() == null) {
            target.setFecRegistro(new Date());
            target.setUsuRegistro(usuario);
        } else {
            target.setFecActualizacion(new Date());
            target.setUsuActualizacion(usuario);
        }
        target.setTemaApp("theme-alternate");
        target.setEstado(EstadoRegistro.ACTIVO.getId());
        target.setIdseller(new Sellers(source.getIdSeller()));
    }

    SellerPersonas toPersonas(ColaboradorSellerMantDto dto, String usuario);

    @Mappings({
        @Mapping(target = "idSeller", source = "sellerPersonas.idseller.id"),})
    ColaboradorSellerMantDto toColaboradorMantDto(SellerPersonas sellerPersonas);
}
