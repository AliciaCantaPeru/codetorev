///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.service.administracion.dto.empresa;
//
//import marketplace.repository.entity.Direcciones;
//import marketplace.repository.entity.Personas;
//import org.mapstruct.BeforeMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.Mappings;
///**
// *
// * @author Martin Pilar mpilarcastillejo@gmail.com
// */
//@Mapper(componentModel = "spring")
//public interface ProveedorMapper {
//
////    List<EmpresaOutDto> toProveedoresOutDtos(List<Proveedores> negocioEntity);
//
//    EmpresaMantDto.PersonasDto toPersonasDto(Personas persona);
//
////    @Mappings({
//////        @Mapping(target = "idDistrito", source = "iddistrito.id"),
//////        @Mapping(target = "idPais", source = "iddepartamento.idpais.id"),
//////        @Mapping(target = "idProvincia", source = "idprovincia.id"),
////        @Mapping(target = "idDepartamento", source = "iddepartamento.id")})
//    EmpresaMantDto.DireccionDto toDireccionDto(Direcciones direccion);
//
////    EmpresaMantDto.EmpresaDto toNegocioDto(Proveedores proveedor);
//
////    @Mappings({
////        @Mapping(target = "iddistrito.id", source = "direccionDto.idDistrito"),
////        @Mapping(target = "idprovincia.id", source = "direccionDto.idProvincia"),
////        @Mapping(target = "iddepartamento.id", source = "direccionDto.idDepartamento")})
//    Direcciones toDireccion(EmpresaMantDto.DireccionDto direccionDto);
//
////    @BeforeMapping
////    default void beforeMapping(@MappingTarget Proveedores target, EmpresaMantDto.EmpresaDto source, String usuario) {
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
////        @Mapping(target = "idpersona.id", source = "idPersona"),
////        @Mapping(target = "iddireccion", source = "direccion")})
////    Proveedores toProveedores(EmpresaMantDto.EmpresaDto clienteDto, Direcciones direccion, Integer idPersona, String usuario);
//
//    @BeforeMapping
//    default void beforeMappingToPerson(@MappingTarget Personas target, EmpresaMantDto.PersonasDto source, String usuario) {
////        if (source.getId() == null) {
////            target.setFecRegistro(new Date());
////            target.setUsuRegistro(usuario);
////        } else {
////            target.setFecActualizacion(new Date());
////            target.setUsuActualizacion(usuario);
////        }
////        target.setEstado(EstadoRegistro.ACTIVO.getId());
//    }
//
//    @Mappings({
//        @Mapping(target = "id", source = "personasDtos.id"),
//        @Mapping(target = "email", source = "personasDtos.email")})
//    Personas toPersonas(EmpresaMantDto.PersonasDto personasDtos, String usuario);
//
////    EmpresaMantDto.RedesSocialesDto toRedesSocialesDto(RedesSociales redesSociales);
//
////    @Mappings({
////        @Mapping(target = "id", source = "redesSocialesDto.id"),
////        @Mapping(target = "nombre", source = "redesSocialesDto.nombre"),
////        @Mapping(target = "idproveedor.id", source = "idNegocio")})
////    RedesSociales toRedesSociale(EmpresaMantDto.RedesSocialesDto redesSocialesDto, Integer idNegocio);
//}
