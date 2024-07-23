///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.service.administracion.dto.empresa;
//
//import marketplace.repository.entity.Direcciones;
//import org.mapstruct.Mapper;
///**
// *
// * @author Martin Pilar mpilarcastillejo@gmail.com
// */
//@Mapper(componentModel = "spring")
//public interface NegocioMapper {
//
////    List<EmpresaOutDto> toNegocioOutDtos(List<UnidadesNegocio> negocioEntity);
//
////    @Mappings({
////        @Mapping(target = "idDistrito", source = "iddistrito.id"),
////        @Mapping(target = "idPais", source = "iddepartamento.idpais.id"),
////        @Mapping(target = "idProvincia", source = "idprovincia.id"),
////        @Mapping(target = "idDepartamento", source = "iddepartamento.id")})
//    EmpresaMantDto.DireccionDto toDireccionDto(Direcciones direccion);
//
////    EmpresaMantDto.EmpresaDto toNegocioDto(UnidadesNegocio cliente);
//
////    @Mappings({
////        @Mapping(target = "iddistrito.id", source = "direccionDto.idDistrito"),
////        @Mapping(target = "idprovincia.id", source = "direccionDto.idProvincia"),
////        @Mapping(target = "iddepartamento.id", source = "direccionDto.idDepartamento")})
//    Direcciones toDireccion(EmpresaMantDto.DireccionDto direccionDto);
//
////    @BeforeMapping
////    default void beforeMapping(@MappingTarget UnidadesNegocio target, EmpresaMantDto.EmpresaDto source, String usuario) {
////        if (source.getId() == null) {
////            target.setFecRegistro(new Date());
////            target.setUsuRegistro(usuario);
////        } else {
////            target.setFecActualizacion(new Date());
////            target.setUsuActualizacion(usuario);
////        }
////        target.setEstado(EstadoRegistro.ACTIVO.getId());
////    }
//
////    @Mappings({
////        @Mapping(target = "id", source = "clienteDto.id"),
////        @Mapping(target = "iddireccion", source = "direccion")})
////    UnidadesNegocio toUnidadesNegocio(EmpresaMantDto.EmpresaDto clienteDto, Direcciones direccion, String usuario);
////
////    EmpresaMantDto.RedesSocialesDto toRedesSocialesDto(RedesSociales redesSociales);
//
////    @Mappings({
////        @Mapping(target = "id", source = "redesSocialesDto.id"),
////        @Mapping(target = "nombre", source = "redesSocialesDto.nombre"),
////        @Mapping(target = "idunidadnegocio.id", source = "idNegocio")})
////    RedesSociales toRedesSociale(EmpresaMantDto.RedesSocialesDto redesSocialesDto, Integer idNegocio);
//
//}
