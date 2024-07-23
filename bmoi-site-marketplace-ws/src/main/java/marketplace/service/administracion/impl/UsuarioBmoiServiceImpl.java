/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import marketplace.logger.ExceptionUser;
import marketplace.repository.RolesRepository;
import marketplace.repository.SellerCuentasRepository;
import marketplace.repository.SellerPersonasRepository;
import marketplace.repository.SellerRepository;
import marketplace.repository.entity.QRoles;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.Roles;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.personas.SellerPersonaOpcionEntity;
import marketplace.service.administracion.UsuarioBmoiService;
import marketplace.service.administracion.dto.usuarioseller.OpcionPersona;
import marketplace.service.administracion.dto.usuarioseller.UsuarioInDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioMantDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioOutDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioSellerMapper;
import marketplace.service.util.EnviarCorreoService;
import marketplace.support.dto.Opcion;
import marketplace.util.Encriptador;
import marketplace.util.EstadoRegistro;
import marketplace.util.Generador;
import marketplace.util.Rol;
import marketplace.util.TipoInterfaz;
import marketplace.util.TipoUsuarioLogin;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioBmoiServiceImpl implements UsuarioBmoiService {

    @Autowired
    private SellerPersonasRepository sellerPersonasRepository;
    @Autowired
    private SellerCuentasRepository sellerCuentasRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UsuarioSellerMapper usuarioMapper;
    @Autowired
    private EnviarCorreoService correoService;
    @Autowired
    private Encriptador encriptador;

    @Override
    public PageImpl<UsuarioOutDto> listar(UsuarioInDto in) throws Exception {
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(in.getIdPersonaLogeada())
                .and(QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.eq(1));
        Optional<SellerCuentas> optionalCuenta = sellerCuentasRepository.findOne(p);
        if (!optionalCuenta.isPresent()) {
            throw new ExceptionUser("La cuenta no esta registrada");
        }
        SellerCuentas cuentaEntity = optionalCuenta.get();
        PageRequest pageRequest = PageRequest.of(in.getPagina(), in.getCantidad());
        boolean esMaster = Rol.esMaster(cuentaEntity.getIdRol().getId());
        Integer idSeller = esMaster ? null : cuentaEntity.getIdSellerpersona().getIdseller().getId();
        PageImpl<SellerPersonas> pageEntity = sellerPersonasRepository.listarSellerPersonas(TipoUsuarioLogin.BMOI.getValor(), in.getSortCampo(), in.getSortOrden(), in.getTexto(), idSeller, pageRequest, true);
        List<UsuarioOutDto> listaclienteOut = usuarioMapper.toUsuarioOutDtos(pageEntity.toList());
        return new PageImpl<>(listaclienteOut, pageRequest, pageEntity.getTotalElements());
    }

    @Transactional
    @Override
    public UsuarioMantDto guardar(UsuarioMantDto mantenimientoDto) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.BMOI.getValor());
        Predicate pPersona = QSellerPersonas.sellerPersonas.id.eq(mantenimientoDto.getIdSellerPersona());
        SellerPersonas persona = sellerPersonasRepository.findOne(pPersona).get();
        String key = persona.getEmail() + ";" + LocalDateTime.now().toString() + ";" + TipoUsuarioLogin.BMOI.getValor();
        String keyEncriptado = encriptador.encriptarTexto(key);
        String pasAleatorio = Generador.generarPassword();
        mantenimientoDto.setContrasenia(pasAleatorio);
        SellerCuentas cuentasEntity = usuarioMapper.toCuentas(mantenimientoDto);
        cuentasEntity.setEstado(EstadoRegistro.INACTIVO.getId());
        cuentasEntity.setKeyNewContrasenia(keyEncriptado);
        sellerCuentasRepository.save(cuentasEntity);
        mantenimientoDto.setId(cuentasEntity.getId());
        String escapeKey = Util.escapeJavascript(keyEncriptado);
        String nombrePersona = persona.getPriNombre() + " " + persona.getSegNombre() + " " + persona.getPriApellido() + " " + persona.getSegApellido() != null ? persona.getSegApellido() : "";
        correoService.enviarCorreoActivacionCuenta(persona.getEmail(), nombrePersona, pasAleatorio, escapeKey);
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public UsuarioMantDto actualizar(UsuarioMantDto mantenimientoDto) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.BMOI.getValor());
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(mantenimientoDto.getIdSellerPersona());
        Optional<SellerCuentas> optionalCuentasEntity = sellerCuentasRepository.findOne(p);
        if (!optionalCuentasEntity.isPresent()) {
            throw new ExceptionUser("La cuenta no esta registrada");
        }
        SellerCuentas cuentas = optionalCuentasEntity.get();
        cuentas.setIdRol(new Roles(mantenimientoDto.getIdRol()));
        sellerCuentasRepository.save(cuentas);
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public void eliminar(int ipPersonaLogeada, List<Integer> listaId) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        for (Integer id : listaId) {
            Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(id);
            Optional<SellerCuentas> optionalCuentasEntity = sellerCuentasRepository.findOne(p);
            if (!optionalCuentasEntity.isPresent()) {
                throw new ExceptionUser("La cuenta no esta registrada");
            }
            SellerCuentas cuentas = optionalCuentasEntity.get();
            sellerCuentasRepository.delete(cuentas);
        }
    }

    @Override
    public UsuarioMantDto obtener(int ipPersonaLogeada, int id) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(id);
        Optional<SellerCuentas> optionalCuentasEntity = sellerCuentasRepository.findOne(p);
        if (!optionalCuentasEntity.isPresent()) {
            throw new ExceptionUser("La cuenta no esta registrada");
        }
        SellerCuentas cuentaEntity = optionalCuentasEntity.get();
        Integer idCliente = sellerRepository.obtenerIdSeller(cuentaEntity.getIdSellerpersona().getId());
        if (idCliente == null) {
            throw new ExceptionUser("El cliente no esta registrada");
        }
        UsuarioMantDto mantenimientoDto = usuarioMapper.toMantenimientoDto(cuentaEntity, idCliente);
        return mantenimientoDto;
    }

    @Override
    public List<Opcion> listarOpcionSeller(int idPersonaLogeada, int idSeller) throws Exception {
        List<Opcion> listaClientes = sellerRepository.listarOpcionSeller(idSeller);
        return listaClientes;
    }

    @Override
    public List<OpcionPersona> listarOpcionPersonas(int idPersonaLogeada, int idSeller, Integer idPersonaSeller) throws Exception {
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.eq(idSeller).and(
                QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(idPersonaLogeada));
        Optional<SellerCuentas> optionalCuenta = sellerCuentasRepository.findOne(p);
        if (!optionalCuenta.isPresent()) {
            throw new ExceptionUser("La cuenta no esta registrada");
        }
        List<SellerPersonaOpcionEntity> listaOpcionEntity = sellerPersonasRepository.listarOpcionPersonaSellerCuenta(TipoUsuarioLogin.BMOI.getValor(), idSeller, idPersonaSeller == -1 ? null : idPersonaSeller);
        List<OpcionPersona> listaOpcionCliente = usuarioMapper.toOpcionPersonas(listaOpcionEntity);
        return listaOpcionCliente;
    }

    @Override
    public List<Opcion> listarOpcionRoles(int idPersonaLogeada) throws Exception {
        sellerCuentasRepository.validaObtenerCorreo(idPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        Predicate pRoles = QRoles.roles.interfaz.eq(TipoInterfaz.WEB.getDescripcion());
        List<Opcion> listaOpcionCliente = rolesRepository.listarRolesPersona(pRoles);
        return listaOpcionCliente;
    }

}
