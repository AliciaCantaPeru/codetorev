///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.service.administracion.dto.empresa;
//
//import java.util.Date;
//import java.util.List;
//import marketplace.repository.entity.Clientes;
//import marketplace.repository.entity.Direcciones;
//import marketplace.repository.entity.Personas;
//import marketplace.util.EstadoRegistro;
//import org.mapstruct.BeforeMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.Mappings;
//
///**
// *
// * @author mpilar
// */
//@Mapper(componentModel = "spring")
//public interface ClientesMapper {
//
//    List<EmpresaOutDto> toClienteOutDtos(List<Clientes> clienteEntity);
//
//    EmpresaMantDto.PersonasDto toPersonasDto(Personas persona);
//
////    EmpresaMantDto.RedesSocialesDto toRedesSocialesDto(SellerRedes redesSociales);
//
////    @Mappings({
////        @Mapping(target = "idDistrito", source = "iddistrito.id"),
////        @Mapping(target = "idPais", source = "iddepartamento.idpais.id"),
////        @Mapping(target = "idProvincia", source = "idprovincia.id"),
////        @Mapping(target = "idDepartamento", source = "iddepartamento.id")})
//    EmpresaMantDto.DireccionDto toDireccionDto(Direcciones direccion);
//
//    EmpresaMantDto.EmpresaDto toClientesDto(Clientes cliente);
//
//    List<EmpresaMantDto.PersonasDto> toPersonasDtos(List<Personas> persona);
//
////    @Mappings({
////        @Mapping(target = "id", source = "redesSocialesDto.id"),
////        @Mapping(target = "nombre", source = "redesSocialesDto.nombre"),
////        @Mapping(target = "idcliente.id", source = "idCliente")})
////    SellerRedes toRedesSociale(EmpresaMantDto.RedesSocialesDto redesSocialesDto, Integer idCliente);
//
////    @Mappings({
////        @Mapping(target = "iddistrito.id", source = "direccionDto.idDistrito"),
////        @Mapping(target = "idprovincia.id", source = "direccionDto.idProvincia"),
////        @Mapping(target = "iddepartamento.id", source = "direccionDto.idDepartamento")})
//    Direcciones toDireccion(EmpresaMantDto.DireccionDto direccionDto);
//
//    @BeforeMapping
//    default void beforeMapping(@MappingTarget Clientes target, EmpresaMantDto.EmpresaDto source, String usuario) {
//        if (source.getId() == null) {
//            target.setFecRegistro(new Date());
//            target.setUsuRegistro(usuario);
//        } else {
//            target.setFecActualizacion(new Date());
//            target.setUsuActualizacion(usuario);
//        }
//        target.setEstado(EstadoRegistro.ACTIVO.getId());
//    }
//
//    @Mappings({
//        @Mapping(target = "id", source = "clienteDto.id"),
//        @Mapping(target = "iddireccion.id", source = "idDireccion")})
//    Clientes toCliente(EmpresaMantDto.EmpresaDto clienteDto, Integer idDireccion, String usuario);
//
//    @BeforeMapping
//    default void beforeMappingToPerson(@MappingTarget Personas target, EmpresaMantDto.PersonasDto source, String usuario, Integer idCliente) {
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
////    @Mappings({
////        @Mapping(target = "id", source = "personasDtos.id"),
////        @Mapping(target = "email", source = "personasDtos.email"),
////        @Mapping(target = "idcliente.id", source = "idCliente")})
//    Personas toPersonas(EmpresaMantDto.PersonasDto personasDtos, String usuario, Integer idCliente);
//
//}
