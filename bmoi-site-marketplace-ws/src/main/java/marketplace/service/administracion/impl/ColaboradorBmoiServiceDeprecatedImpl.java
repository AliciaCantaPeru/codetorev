///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.service.administracion.impl;
//
//import com.querydsl.core.types.Predicate;
//import java.util.List;
//import java.util.Optional;
//import marketplace.logger.ExceptionUser;
//import marketplace.repository.CuentasRepository;
//import marketplace.repository.PersonasRepository;
//import marketplace.repository.entity.Cuentas;
//import marketplace.repository.entity.Personas;
//import marketplace.repository.entity.QCuentas;
//import marketplace.service.administracion.ColaboradorBmoiService;
//import marketplace.service.administracion.dto.colaboradorbmoi.ColaboradorBmoiInDto;
//import marketplace.service.administracion.dto.colaboradorbmoi.ColaboradorBmoiMantDto;
//import marketplace.service.administracion.dto.colaboradorbmoi.ColaboradorBmoiMapper;
//import marketplace.service.administracion.dto.colaboradorbmoi.ColaboradorBmoiOutDto;
//import marketplace.service.util.AmazonClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// *
// * @author Martin Pilar
// */
//@Service
//public class ColaboradorBmoiServiceImpl implements ColaboradorBmoiService {
//
//    @Autowired
//    private PersonasRepository personasRepository;
//    @Autowired
//    private ColaboradorBmoiMapper colaboradorMapper;
//    @Autowired
//    private CuentasRepository cuentasRepository;
//    @Autowired
//    private AmazonClientService amazonClientService;
////    @Autowired
////    private SolicitudesRepository solicitudesRepository;
//
//    @Override
//    public PageImpl<ColaboradorBmoiOutDto> listar(ColaboradorBmoiInDto in) throws Exception {
//        PageRequest pageRequest = PageRequest.of(in.getPagina(), in.getCantidad());
//        Predicate p = QCuentas.cuentas.idPersona.id.eq(in.getIdPersonaLogeada());
//        Optional<Cuentas> optionalCuenta = cuentasRepository.findOne(p);
//        if (!optionalCuenta.isPresent()) {
//            throw new ExceptionUser("La cuenta no esta registrada");
//        }
//        PageImpl<Personas> pageEntity = personasRepository.listarPersonasColaboradores(in.getSortCampo(), in.getSortOrden(), in.getTexto(), pageRequest, false);
//        List<ColaboradorBmoiOutDto> listaclienteOut = colaboradorMapper.toColaboradorOutDto(pageEntity.toList());
//        return new PageImpl<>(listaclienteOut, pageRequest, pageEntity.getTotalElements());
//    }
//
//    @Override
//    public ColaboradorBmoiMantDto obtener(int ipPersonaLogeada, int id) throws Exception {
//        cuentasRepository.validaObtenerCorreo(ipPersonaLogeada);
//        Optional<Personas> optionalPersona = personasRepository.findById(id);
//        if (!optionalPersona.isPresent()) {
//            throw new ExceptionUser("El colaborador no esta registrada");
//        }
//        Personas persona = optionalPersona.get();
//        ColaboradorBmoiMantDto mantenimientoDto = colaboradorMapper.toColaboradorMantDto(persona);
//        return mantenimientoDto;
//    }
//
//    @Transactional
//    @Override
//    public ColaboradorBmoiMantDto guardar(MultipartFile image, ColaboradorBmoiMantDto mantenimientoDto) throws Exception {
//        String usuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada());
//        String foto = null;
//        if (image != null && !image.isEmpty()) {
//            foto = amazonClientService.uploadFile(image, "sellers-colaboradores");
//        }
//        Personas personas = colaboradorMapper.toPersonas(mantenimientoDto, usuario);
//        personas.setFoto(foto);
//        personasRepository.save(personas);
//        return mantenimientoDto;
//    }
//
//    @Override
//    @Transactional
//    public ColaboradorBmoiMantDto actualizar(MultipartFile image, ColaboradorBmoiMantDto mantenimientoDto) throws Exception {
//        String usuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada());
//        Optional<Personas> personaEntity = personasRepository.findById(mantenimientoDto.getId());
//        if (!personaEntity.isPresent()) {
//            throw new ExceptionUser("El colaborador no esta registrado");
//        }
//        Personas personaBD = personaEntity.get();
//        String foto = null;
//        if (image != null && !image.isEmpty()) {
//            if (personaBD.getFoto() != null) {
//                amazonClientService.deleteFile(personaBD.getFoto());
//            }
//            foto = amazonClientService.uploadFile(image, "sellers-colaboradores");
//        }
//        Personas personasUpdate = colaboradorMapper.toPersonas(mantenimientoDto, usuario);
//        personasUpdate.setFoto(foto != null ? foto : personaBD.getFoto());
//        personasRepository.save(personasUpdate);
//        return mantenimientoDto;
//    }
//
//    @Transactional
//    @Override
//    public void eliminar(int ipPersonaLogeada, List<Integer> listaId) throws Exception {
//        cuentasRepository.validaObtenerCorreo(ipPersonaLogeada);
//        for (Integer id : listaId) {
//            Optional<Personas> personaEntity = personasRepository.findById(id);
//            if (!personaEntity.isPresent()) {
//                throw new ExceptionUser("El colaborador no esta registrado");
//            }
//            Personas persona = personaEntity.get();
//            if (persona.getId() == ipPersonaLogeada) {
//                throw new ExceptionUser("Un usuario logeado no puede autoeliminarse");
//            }
//            Cuentas cuenta = persona.getCuentas();
//            if (cuenta != null) {
//                cuentasRepository.delete(cuenta);
//            }
//            personasRepository.delete(persona);
//        }
//    }
//}
