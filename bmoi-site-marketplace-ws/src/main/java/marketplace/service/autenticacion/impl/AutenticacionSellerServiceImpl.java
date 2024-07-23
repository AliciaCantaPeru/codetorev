/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.autenticacion.impl;

import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import marketplace.logger.ExceptionUser;
import marketplace.repository.ConfiguracionesRepository;
import marketplace.repository.CuentasRepository;
import marketplace.repository.SellerCuentasRepository;
import marketplace.repository.SellerPersonasRepository;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.SellerCuentas;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.cuentas.PersonaCuentaEntity;
import marketplace.repository.entity.cuentas.UsuarioEntity;
import marketplace.security.util.JwtTokenUtil;
import marketplace.security.util.JwtUser;
import marketplace.service.administracion.dto.autenticacion.AutUsuarioOutDto;
import marketplace.service.administracion.dto.autenticacion.CambiarContraseniaInDto;
import marketplace.service.administracion.dto.autenticacion.KeyInDto;
import marketplace.service.autenticacion.AutenticacionSellerService;
import marketplace.service.util.EnviarCorreoService;
import marketplace.util.Encriptador;
import marketplace.util.EstadoRegistro;
import marketplace.util.TipoConfiguracion;
import marketplace.util.TipoUsuarioLogin;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PCADMIN
 */
@Service
public class AutenticacionSellerServiceImpl implements AutenticacionSellerService {

    @Autowired
    private SellerPersonasRepository sellerPersonasRepository;
    @Autowired
    private SellerCuentasRepository sellerCuentasRepository;
    @Autowired
    private CuentasRepository cuentasRepository;
    @Autowired
    private ConfiguracionesRepository configuracionesRepository;
    @Autowired
    private EnviarCorreoService correoService;
    @Autowired
    private Encriptador encriptador;

    public static String SEPARATOR_USERNAME = "@-@";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            String[] splitUserName = username.split(SEPARATOR_USERNAME);
            String email = splitUserName[1];
            String tipoUsuario = splitUserName[0];
            UsuarioEntity usuarioEntity = sellerCuentasRepository.obtenerUsuario(email, tipoUsuario);
//            if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
//                usuarioEntity = cuentasRepository.obtenerUsuario(email);
//            }
//            if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
//            usuarioEntity = sellerCuentasRepository.obtenerUsuario(email, tipoUsuario);
//            }
            if (usuarioEntity == null) {
                throw new ExceptionUser("Credenciales incorrectas.");
            }
            String pass = encriptador.desencriptarTexto(usuarioEntity.getContrasenia());
            String idUsuario = encriptador.encriptarID(usuarioEntity.getIdPersona().toString());
            Set<GrantedAuthority> grantedAuthorities = new HashSet();
            grantedAuthorities.add(new SimpleGrantedAuthority(usuarioEntity.getRol()));
            return new JwtUser(username, idUsuario, pass, grantedAuthorities);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Credenciales incorrectas.");
        }
    }

    @Override
    public AutUsuarioOutDto obtenerUsuario(String idUsuario) throws Exception {
        Integer idUser = encriptador.desencriptarIDString(idUsuario);
        Predicate p = QSellerCuentas.sellerCuentas.idSellerpersona.id.eq(idUser);
        SellerCuentas cuenta = sellerCuentasRepository.findOne(p).get();
        AutUsuarioOutDto usuarioOutDto = new AutUsuarioOutDto();
        usuarioOutDto.setIdSellerPersona(cuenta.getIdSellerpersona().getId());
        usuarioOutDto.setCorreo(cuenta.getIdSellerpersona().getEmail());
        usuarioOutDto.setIdRol(cuenta.getIdRol().getId());
        usuarioOutDto.setIdSeller(cuenta.getIdSellerpersona().getIdseller().getId());
        usuarioOutDto.setEmpresa(cuenta.getIdSellerpersona().getIdseller().getNomComercial());
        usuarioOutDto.setPlan(cuenta.getIdSellerpersona().getIdseller().getSellerPlanSet().isEmpty() ? null : cuenta.getIdSellerpersona().getIdseller().getSellerPlanSet().iterator().next().getIdPlan().getNombre());
        usuarioOutDto.setNombres(cuenta.getIdSellerpersona().getPriNombre() + " " + cuenta.getIdSellerpersona().getSegNombre() + " " + cuenta.getIdSellerpersona().getPriApellido() + " " + cuenta.getIdSellerpersona().getSegApellido());
        usuarioOutDto.setRol(cuenta.getIdRol().getNombre());
        usuarioOutDto.setTemaApp(cuenta.getIdSellerpersona().getTemaApp());
        usuarioOutDto.setTipoUsuario(cuenta.getIdSellerpersona().getIdseller().getId() == 1 ? TipoUsuarioLogin.BMOI.getValor() : TipoUsuarioLogin.SELLER.getValor());
        return usuarioOutDto;
    }

    @Transactional
    @Override
    public void cambiarContraseniaEnviarLink(String correo, String tipoUsuario) throws Exception {
        PersonaCuentaEntity personaCuenta = sellerCuentasRepository.existeCuenta(correo, tipoUsuario);
        if (personaCuenta == null) {
            throw new ExceptionUser("El e-mail ingresado no existe");
        }
        String key = correo + ";" + LocalDateTime.now().toString() + ";" + tipoUsuario;
        String encriptado = encriptador.encriptarTexto(key);
        String escapeKey = Util.escapeJavascript(encriptado);
        sellerCuentasRepository.updateKeyNewPassword(personaCuenta.getId(), encriptado);
        correoService.enviarCorreoRecuperarContrasenia(correo, personaCuenta.getNombres(), escapeKey);
    }

    @Override
    public String cambiarContraseniaVerficarKey(KeyInDto keyInDto) throws Exception {
        String unescape = Util.unescapeJavascript(keyInDto.getValor());
        String key = encriptador.desencriptarTexto(unescape);
        String Correo = key.split(";")[0];
        boolean existeCuenta = sellerCuentasRepository.existeKey(Correo, unescape);
        if (!existeCuenta) {
            throw new ExceptionUser("El link para para realizar esta operación no es válido");
        }
        String fechaSolicitudString = key.split(";")[1];
        LocalDateTime fechaSolicitud = LocalDateTime.parse(fechaSolicitudString);
        LocalDateTime fechaActual = LocalDateTime.now();
        String valor = configuracionesRepository.obtenerValor(TipoConfiguracion.TIEMPO_EXPIRACION_CORREO.getValor());
        int horasValidos = Integer.parseInt(valor);
        if (horasValidos != -1) {
            fechaSolicitud = fechaSolicitud.plusHours(horasValidos);
            int compare = fechaSolicitud.compareTo(fechaActual);// 4 - 5 =  -1  0  
            if (compare < 0) {
                throw new ExceptionUser("El link para reestablecer su contraseña expiró");
            }
        }
        return keyInDto.getValor();
    }

    @Transactional
    @Override
    public void cambiarContrasenia(CambiarContraseniaInDto cambiarContraseniaInDto) throws Exception {
        String unescape = Util.unescapeJavascript(cambiarContraseniaInDto.getKey());
        String key = encriptador.desencriptarTexto(unescape);
        String Correo = key.split(";")[0];
        String tipoUsuario = key.split(";")[2];
        Predicate p = null;
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            p = QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.eq(1)
                    .and(QSellerCuentas.sellerCuentas.idSellerpersona.email.eq(Correo));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            p = QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.ne(1)
                    .and(QSellerCuentas.sellerCuentas.idSellerpersona.email.eq(Correo));
        }

        Optional<SellerCuentas> optCuenta = sellerCuentasRepository.findOne(p);
        if (!optCuenta.isPresent()) {
            throw new ExceptionUser("Operación denegada");
        }
        SellerCuentas cuenta = optCuenta.get();
        String contraseniaEncriptada = encriptador.encriptarTexto(cambiarContraseniaInDto.getContrasenia());
        cuenta.setContrasenia(contraseniaEncriptada);
        sellerCuentasRepository.save(cuenta);
        sellerCuentasRepository.updateKeyNewPassword(cuenta.getIdSellerpersona().getId(), null);
    }

    @Transactional
    @Override
    public void activarCuenta(CambiarContraseniaInDto cambiarContraseniaInDto) throws Exception {
        String unescape = Util.unescapeJavascript(cambiarContraseniaInDto.getKey());
        String key = encriptador.desencriptarTexto(unescape);
        String Correo = key.split(";")[0];
        String tipoUsuario = key.split(";")[2];
        Predicate p = null;
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            p = QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.eq(1)
                    .and(QSellerCuentas.sellerCuentas.idSellerpersona.email.eq(Correo));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            p = QSellerCuentas.sellerCuentas.idSellerpersona.idseller.id.ne(1)
                    .and(QSellerCuentas.sellerCuentas.idSellerpersona.email.eq(Correo));
        }
        Optional<SellerCuentas> optCuenta = sellerCuentasRepository.findOne(p);
        if (!optCuenta.isPresent()) {
            throw new ExceptionUser("Operación denegada");
        }
        SellerCuentas cuenta = optCuenta.get();
        if (cuenta.getEstado() == EstadoRegistro.ACTIVO.getId()) {
            throw new ExceptionUser("La cuenta ya fue activada");
        }
        String contraseniaEncriptada = encriptador.encriptarTexto(cambiarContraseniaInDto.getContrasenia());
        cuenta.setContrasenia(contraseniaEncriptada);
        cuenta.setEstado(EstadoRegistro.ACTIVO.getId());
        sellerCuentasRepository.save(cuenta);
        sellerCuentasRepository.updateKeyNewPassword(cuenta.getIdSellerpersona().getId(), null);
    }

    @Transactional
    @Override
    public String guardarTema(int idSellerPersona, String tema) throws Exception {
        Predicate p = QSellerPersonas.sellerPersonas.id.eq(idSellerPersona);
        Optional<SellerPersonas> optSellerPersona = sellerPersonasRepository.findOne(p);
        if (!optSellerPersona.isPresent()) {
            throw new ExceptionUser("El usuario no existe");
        }
        SellerPersonas sellerPersona = optSellerPersona.get();
        sellerPersona.setTemaApp(tema);
        sellerPersonasRepository.save(sellerPersona);
        String idUser = encriptador.encriptarID(idSellerPersona + "");
        AutUsuarioOutDto autUsuarioOutDto = obtenerUsuario(idUser);
        String userName = autUsuarioOutDto.getTipoUsuario() + SEPARATOR_USERNAME + sellerPersona.getEmail();
        UserDetails userDetails = loadUserByUsername(userName);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", autUsuarioOutDto);
        final String token = jwtTokenUtil.generateToken(claims, userDetails);
        return token;
    }
}
