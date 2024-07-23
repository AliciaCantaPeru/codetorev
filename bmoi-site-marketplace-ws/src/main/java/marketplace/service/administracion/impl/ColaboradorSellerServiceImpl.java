/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Optional;
import marketplace.logger.ExceptionUser;
import marketplace.repository.SellerCuentasRepository;
import marketplace.repository.SellerPersonasRepository;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.SellerPersonas;
import marketplace.service.administracion.ColaboradorSellerService;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerInDto;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerMantDto;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerMapper;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerOutDto;
import marketplace.service.util.AmazonClientService;
import marketplace.util.Rol;
import marketplace.util.TipoUsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar
 */
@Service
public class ColaboradorSellerServiceImpl implements ColaboradorSellerService {

    @Autowired
    private SellerPersonasRepository personasRepository;
    @Autowired
    private ColaboradorSellerMapper colaboradorMapper;
    @Autowired
    private SellerCuentasRepository cuentasRepository;
    @Autowired
    private AmazonClientService amazonClientService;
//    @Autowired
//    private SolicitudesRepository solicitudesRepository;

    @Override
    public PageImpl<ColaboradorSellerOutDto> listar(ColaboradorSellerInDto in) throws Exception {
        PageRequest pageRequest = PageRequest.of(in.getPagina(), in.getCantidad());
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(in.getIdPersonaLogeada())
                .and(QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.ne(1));
        Optional<SellerCuentas> optionalCuenta = cuentasRepository.findOne(p);
        if (!optionalCuenta.isPresent()) {
            throw new ExceptionUser("La cuenta no esta registrada");
        }
        SellerCuentas cuentaEntity = optionalCuenta.get();
        boolean esMaster = Rol.esMaster(cuentaEntity.getIdRol().getId());
        Integer idSeller = esMaster ? null : cuentaEntity.getIdSellerpersona().getIdseller().getId();
        PageImpl<SellerPersonas> pageEntity = personasRepository.listarSellerPersonas(TipoUsuarioLogin.SELLER.getValor(), in.getSortCampo(), in.getSortOrden(), in.getTexto(), idSeller, pageRequest, false);
        List<ColaboradorSellerOutDto> listaclienteOut = colaboradorMapper.toColaboradorOutDto(pageEntity.toList());
        return new PageImpl<>(listaclienteOut, pageRequest, pageEntity.getTotalElements());
    }

    @Override
    public ColaboradorSellerMantDto obtener(int ipPersonaLogeada, int id) throws Exception {
        cuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.SELLER.getValor());
        Optional<SellerPersonas> optionalPersona = personasRepository.findById(id);
        if (!optionalPersona.isPresent()) {
            throw new ExceptionUser("El colaborador no esta registrada");
        }
        SellerPersonas persona = optionalPersona.get();
        ColaboradorSellerMantDto mantenimientoDto = colaboradorMapper.toColaboradorMantDto(persona);
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public ColaboradorSellerMantDto guardar(MultipartFile image, ColaboradorSellerMantDto mantenimientoDto) throws Exception {
        String usuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.SELLER.getValor());
        String foto = null;
        if (image != null && !image.isEmpty()) {
            foto = amazonClientService.uploadFile(image, "sellers-colaboradores/");
        }
        SellerPersonas personas = colaboradorMapper.toPersonas(mantenimientoDto, usuario);
        personas.setFoto(foto);
        personas.setCntPrincipal(0);
        personasRepository.save(personas);
        return mantenimientoDto;
    }

    @Override
    @Transactional
    public ColaboradorSellerMantDto actualizar(MultipartFile image, ColaboradorSellerMantDto mantenimientoDto) throws Exception {
        String usuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.SELLER.getValor());
        Optional<SellerPersonas> personaEntity = personasRepository.findById(mantenimientoDto.getId());
        if (!personaEntity.isPresent()) {
            throw new ExceptionUser("El colaborador no esta registrado");
        }
        SellerPersonas personaBD = personaEntity.get();
        String foto = null;
        if (image != null && !image.isEmpty()) {
            if (personaBD.getFoto() != null) {
                amazonClientService.deleteFile(personaBD.getFoto());
            }
            foto = amazonClientService.uploadFile(image, "sellers-colaboradores/");
        }
        SellerPersonas personasUpdate = colaboradorMapper.toPersonas(mantenimientoDto, usuario);
        personasUpdate.setTemaApp(personaBD.getTemaApp());
        personasUpdate.setFecRegistro(personaBD.getFecRegistro());
        personasUpdate.setUsuRegistro(personaBD.getUsuRegistro());
        personasUpdate.setCntPrincipal(personaBD.getCntPrincipal());
        personasUpdate.setFoto(foto != null ? foto : personaBD.getFoto());
        personasRepository.save(personasUpdate);
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public void eliminar(int ipPersonaLogeada, List<Integer> listaId) throws Exception {
        cuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.SELLER.getValor());
        for (Integer id : listaId) {
            Optional<SellerPersonas> personaEntity = personasRepository.findById(id);
            if (!personaEntity.isPresent()) {
                throw new ExceptionUser("El colaborador no esta registrado");
            }
            SellerPersonas persona = personaEntity.get();
            if (persona.getCntPrincipal() == 1) {
                throw new ExceptionUser("No se puede eliminar al colaborador principal del seller");
            }
            if (persona.getId() == ipPersonaLogeada) {
                throw new ExceptionUser("Un usuario logeado no puede autoeliminarse");
            }
//        Predicate p = QSolicitudes.solicitudes.idpersonanegocio.id.eq(id);
//        boolean conSolicitud = solicitudesRepository.exists(p);
//        if (conSolicitud) {
//            throw new ExceptionUser("No se puede eliminar a un colaborador asociado a una solicitud");
//        }
            SellerCuentas cuenta = persona.getSellerCuentas();
            if (cuenta != null) {
                cuentasRepository.delete(cuenta);
            }
            personasRepository.delete(persona);
        }
    }
}
