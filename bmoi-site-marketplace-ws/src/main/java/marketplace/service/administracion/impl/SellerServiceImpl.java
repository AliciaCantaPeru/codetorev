/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import marketplace.logger.ExceptionUser;
import marketplace.repository.DireccionesRepository;
import marketplace.repository.ProductosRepository;
import marketplace.repository.SellerCuentasRepository;
import marketplace.repository.SellerPersonasRepository;
import marketplace.repository.SellerRedesRepository;
import marketplace.repository.SellerRepository;
import marketplace.repository.entity.Direcciones;
import marketplace.repository.entity.QProductos;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.QSellerRedes;
import marketplace.repository.entity.Roles;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.SellerRedes;
import marketplace.repository.entity.Sellers;
import marketplace.repository.entity.sellers.SellerEntity;
import marketplace.service.administracion.SellerService;
import marketplace.service.administracion.dto.seller.SellerInDto;
import marketplace.service.administracion.dto.seller.SellerMantDto;
import marketplace.service.administracion.dto.seller.SellerMapper;
import marketplace.service.administracion.dto.seller.SellerOutDto;
import marketplace.service.util.AmazonClientService;
import marketplace.service.util.EnviarCorreoService;
import marketplace.util.Encriptador;
import marketplace.util.EstadoRegistro;
import marketplace.util.Generador;
import marketplace.util.Rol;
import marketplace.util.TipoUsuarioLogin;
import marketplace.util.Util;
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
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private DireccionesRepository direccionesRepository;
    @Autowired
    private SellerPersonasRepository personasRepository;
    @Autowired
    private SellerCuentasRepository cuentasRepository;
    @Autowired
    private SellerRedesRepository redesRepository;
    @Autowired
    private AmazonClientService amazonClientService;
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private SellerPersonasRepository sellerPersonasRepository;
    @Autowired
    private SellerCuentasRepository sellerCuentasRepository;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private Encriptador encriptador;
    @Autowired
    private EnviarCorreoService correoService;

    @Override
    public PageImpl<SellerOutDto> listar(SellerInDto in) throws Exception {
        PageRequest pageRequest = PageRequest.of(in.getPagina(), in.getCantidad());
        cuentasRepository.validaObtenerCorreo(in.getIdPersonaLogeada(), TipoUsuarioLogin.BMOI.getValor());
        PageImpl<SellerEntity> pageEntity = sellerRepository.listarSeller(in.getSortCampo(), in.getSortOrden(), in.getTexto(), pageRequest);
        List<SellerOutDto> listaclienteOut = sellerMapper.toSellerOutDtos(pageEntity.toList());
        return new PageImpl<>(listaclienteOut, pageRequest, pageEntity.getTotalElements());
    }

    @Override
    public SellerMantDto obtener(int ipPersonaLogeada, int id) throws Exception {
        cuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        Optional<Sellers> sellerOpt = sellerRepository.findById(id);
        if (!sellerOpt.isPresent()) {
            throw new ExceptionUser("El seller no esta registrado");
        }
        Sellers seller = sellerOpt.get();
        SellerMantDto mantenimientoDto = new SellerMantDto();
        Predicate pPersona = QSellerPersonas.sellerPersonas.idseller.id.eq(seller.getId());
        List<SellerPersonas> listaPersonas = Lists.newArrayList(personasRepository.findAll(pPersona));
        Predicate pRed = QSellerRedes.sellerRedes.idSeller.id.eq(seller.getId());
        List<SellerRedes> listaRedesSociales = Lists.newArrayList(redesRepository.findAll(pRed));
        List<SellerMantDto.PersonasDto> listPersonasDto = listaPersonas.stream().map(cliPer -> sellerMapper.toPersonasDto(cliPer)).collect(Collectors.toList());
        List<SellerMantDto.RedesSocialesDto> listaRedesDto = listaRedesSociales.stream().map(red -> sellerMapper.toRedesSocialesDto(red)).collect(Collectors.toList());
        mantenimientoDto.setEmpresa(sellerMapper.toSellerDto(seller));
        mantenimientoDto.setListaPersonas(listPersonasDto);
        mantenimientoDto.setListaRedesSociales(listaRedesDto);
        mantenimientoDto.setDireccion(sellerMapper.toDireccionDto(seller.getIddireccion()));
        mantenimientoDto.setIdPersonaLogeada(ipPersonaLogeada);
        mantenimientoDto.setFoto(seller.getImg());
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public SellerMantDto guardar(MultipartFile image, SellerMantDto mantenimientoDto) throws Exception {
        String correoUsuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.BMOI.getValor());
        Direcciones direccionEntity = sellerMapper.toDireccion(mantenimientoDto.getDireccion());
        direccionesRepository.save(direccionEntity);
        String foto = null;
        if (image != null && !image.isEmpty()) {
            foto = amazonClientService.uploadFile(image, "sellers/");
        }
        Sellers sellerEntity = sellerMapper.toSeller(mantenimientoDto.getEmpresa(), direccionEntity.getId(), correoUsuario);
        sellerEntity.setImg(foto);
        sellerRepository.save(sellerEntity);
        List<SellerRedes> redesEntity = mantenimientoDto.getListaRedesSociales().stream().map(redSocialDto -> sellerMapper.toRedesSociale(redSocialDto, sellerEntity.getId())).collect(Collectors.toList());
        redesRepository.saveAll(redesEntity);
        List<SellerPersonas> listaPersonasEntity = mantenimientoDto.getListaPersonas().stream().map(perDto -> sellerMapper.toPersonas(perDto, correoUsuario, sellerEntity.getId())).collect(Collectors.toList());
        personasRepository.saveAll(listaPersonasEntity);
        activarcuenta(mantenimientoDto.getIdPersonaLogeada(), sellerEntity.getId());
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public SellerMantDto actualizar(MultipartFile image, SellerMantDto mantenimientoDto) throws Exception {
        String correoUsuario = cuentasRepository.validaObtenerCorreo(mantenimientoDto.getIdPersonaLogeada(), TipoUsuarioLogin.BMOI.getValor());
        Direcciones direccionEntity = sellerMapper.toDireccion(mantenimientoDto.getDireccion());
        direccionesRepository.save(direccionEntity);

        Sellers clienteBd = sellerRepository.findById(mantenimientoDto.getEmpresa().getId()).get();
        String foto = null;
        if (image != null && !image.isEmpty()) {
            if (clienteBd.getImg() != null) {
                amazonClientService.deleteFile(clienteBd.getImg());
            }
            foto = amazonClientService.uploadFile(image, "sellers/");
        }
        Sellers sellerEntity = sellerMapper.toSeller(mantenimientoDto.getEmpresa(), direccionEntity.getId(), correoUsuario);
        sellerEntity.setFecRegistro(clienteBd.getFecRegistro());
        sellerEntity.setUsuRegistro(clienteBd.getUsuRegistro());
        sellerEntity.setTotPedMonto(clienteBd.getTotPedMonto());
        sellerEntity.setTotPedidos(clienteBd.getTotPedidos());
        sellerEntity.setImg(foto != null ? foto : clienteBd.getImg());
        sellerRepository.save(sellerEntity);
        Predicate pRed = QSellerRedes.sellerRedes.idSeller.id.eq(sellerEntity.getId());
        List<SellerRedes> listaRedesSocialesBd = Lists.newArrayList(redesRepository.findAll(pRed));
        Predicate pPersona = QSellerPersonas.sellerPersonas.idseller.id.eq(sellerEntity.getId());
        List<SellerPersonas> listaPersonasBd = Lists.newArrayList(personasRepository.findAll(pPersona));
        List<SellerRedes> redesEliminar = new ArrayList<>();
        List<SellerPersonas> personasEliminar = new ArrayList<>();
        listaRedesSocialesBd.forEach(red -> {
            Optional<SellerMantDto.RedesSocialesDto> redEncontrado = mantenimientoDto.getListaRedesSociales().stream().filter(redSocialDto -> redSocialDto.getId() != null && redSocialDto.getId().equals(red.getId())).findFirst();
            if (!redEncontrado.isPresent()) {
                redesEliminar.add(red);
            }
        });
        listaPersonasBd.forEach(persona -> {
            Optional<SellerMantDto.PersonasDto> personaDto = mantenimientoDto.getListaPersonas().stream().filter(perDto -> perDto.getId() != null && perDto.getId().equals(persona.getId())).findFirst();
            if (!personaDto.isPresent()) {
                personasEliminar.add(persona);
            }
        });
        List<SellerRedes> redesEntity = mantenimientoDto.getListaRedesSociales().stream().map(redSocialDto -> sellerMapper.toRedesSociale(redSocialDto, sellerEntity.getId())).collect(Collectors.toList());
        List<SellerPersonas> listaPersonasEntity = mantenimientoDto.getListaPersonas().stream().map(perDto -> sellerMapper.toPersonas(perDto, correoUsuario, sellerEntity.getId())).collect(Collectors.toList());
        listaPersonasEntity.forEach(persona -> {
            Optional<SellerPersonas> personaEncontrada = listaPersonasBd.stream().filter(perEntity -> perEntity.getId() != null && perEntity.getId().equals(persona.getId())).findFirst();
            if (personaEncontrada.isPresent()) {
                persona.setFecRegistro(personaEncontrada.get().getFecRegistro());
                persona.setUsuRegistro(personaEncontrada.get().getUsuRegistro());
                persona.setFoto(personaEncontrada.get().getFoto());
            }
        });

        redesRepository.saveAll(redesEntity);
        personasRepository.saveAll(listaPersonasEntity);
        redesRepository.deleteAll(redesEliminar);
        personasRepository.deleteAll(personasEliminar);
        return mantenimientoDto;
    }

    @Transactional
    @Override
    public void eliminar(int ipPersonaLogeada, List<Integer> listaSeller) throws Exception {
        String correoUsuario = cuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        for (Integer idSeller : listaSeller) {
            Optional<Sellers> clienteEntity = sellerRepository.findById(idSeller);
            if (!clienteEntity.isPresent()) {
                throw new ExceptionUser("El seller no esta registrado");
            }
            Predicate p = QProductos.productos.idSeller.id.eq(idSeller);
            boolean conProducto = productosRepository.exists(p);
            Sellers cliente = clienteEntity.get();
            if (conProducto) {
                throw new ExceptionUser("No se puede eliminar al seller " + cliente.getRazSocial()
                        + " ya que tiene asociado un producto");
            }
            cliente.setEstado(EstadoRegistro.INACTIVO.getId());
            cliente.setUsuActualizacion(correoUsuario);
            cliente.setFecActualizacion(new Date());
            sellerRepository.save(cliente);
        }

    }

    @Override
    @Transactional
    public void activarcuenta(int ipPersonaLogeada, int idSeller) throws Exception {
        String correoUsuario = cuentasRepository.validaObtenerCorreo(ipPersonaLogeada, TipoUsuarioLogin.BMOI.getValor());
        Optional<Sellers> sellerOpt = sellerRepository.findById(idSeller);
        if (!sellerOpt.isPresent()) {
            throw new ExceptionUser("El seller no esta registrado");
        }
        Sellers seller = sellerOpt.get();
        Predicate pPersona = QSellerPersonas.sellerPersonas.idseller.id.eq(seller.getId()).and(QSellerPersonas.sellerPersonas.cntPrincipal.eq(1));
        SellerPersonas persona = personasRepository.findOne(pPersona).get();
        String key = persona.getEmail() + ";" + LocalDateTime.now().toString() + ";" + TipoUsuarioLogin.SELLER.getValor();
        String keyEncriptado = encriptador.encriptarTexto(key);
        String pasAleatorio = Generador.generarPassword();
        SellerCuentas cuentasEntity = persona.getSellerCuentas() == null ? new SellerCuentas() : persona.getSellerCuentas();
        cuentasEntity.setEstado(cuentasEntity.getEstado() != 0 ? cuentasEntity.getEstado() : EstadoRegistro.INACTIVO.getId());
        cuentasEntity.setKeyNewContrasenia(keyEncriptado);
        cuentasEntity.setFecRegistro(cuentasEntity.getFecRegistro() != null ? cuentasEntity.getFecRegistro() : new Date());
        cuentasEntity.setIdRol(new Roles(Rol.ADMIN.getId()));
        cuentasEntity.setIdSellerpersona(persona);
        cuentasEntity.setEstado(idSeller);
        cuentasEntity.setContrasenia(pasAleatorio);
        sellerCuentasRepository.save(cuentasEntity);
        String escapeKey = Util.escapeJavascript(keyEncriptado);
        String nombrePersona = persona.getPriNombre() + " " + persona.getSegNombre() + " " + persona.getPriApellido() + " " + persona.getSegApellido() != null ? persona.getSegApellido() : "";
        correoService.enviarCorreoActivacionCuenta(persona.getEmail(), nombrePersona, pasAleatorio, escapeKey);
    }

}
