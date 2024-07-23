/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.general;

import java.util.List;
import marketplace.repository.entity.Departamentos;
import marketplace.repository.entity.Distritos;
import marketplace.repository.entity.Paises;
import marketplace.repository.entity.Provincias;
import marketplace.repository.entity.TblmasterOpciones;
import marketplace.repository.entity.TblmasterParametros;
import marketplace.repository.entity.ValorCambio;
import marketplace.support.dto.Opcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author PCADMIN
 */
@Mapper(componentModel = "spring")
public interface GeneralMapper {

    List<ParametroDto> toParametroDtos(Iterable<TblmasterParametros> parametros);

    List<OpcionDto> toOpcionDtos(Iterable<TblmasterOpciones> opciones);

    List<Opcion> toUbigeoOutPaises(Iterable<Paises> paises);

    List<Opcion> toUbigeoOutDepartamentos(Iterable<Departamentos> departamentos);

    List<Opcion> toUbigeoOutProvincias(Iterable<Provincias> provincias);

    List<TipoCambioDto> toTipoCambioDtos(Iterable<ValorCambio> valorCambio);

    List<Opcion> toUbigeoOutDistritos(Iterable<Distritos> distritos);

    Opcion toUbigeoOutPaises(Paises pais);

    Opcion toUbigeoOutDepartamento(Departamentos departamento);

    @Mappings({
        @Mapping(target = "nombre", source = "provincia.nomProvincia")
    })
    Opcion toUbigeoOutProvincia(Provincias provincia);

    Opcion toUbigeoOutDistrito(Distritos distrito);
}
