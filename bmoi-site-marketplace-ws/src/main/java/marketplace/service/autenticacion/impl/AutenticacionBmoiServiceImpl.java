/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.autenticacion.impl;

import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Optional;
import marketplace.logger.ExceptionUser;
import marketplace.repository.ConfiguracionesRepository;
import marketplace.repository.CuentasRepository;
import marketplace.repository.entity.Cuentas;
import marketplace.repository.entity.QCuentas;
import marketplace.repository.entity.cuentas.PersonaCuentaEntity;
import marketplace.service.administracion.dto.autenticacion.AutUsuarioOutDto;
import marketplace.service.administracion.dto.autenticacion.CambiarContraseniaInDto;
import marketplace.service.administracion.dto.autenticacion.KeyInDto;
import marketplace.service.autenticacion.AutenticacionBmoiService;
import marketplace.service.util.EnviarCorreoService;
import marketplace.util.Encriptador;
import marketplace.util.EstadoRegistro;
import marketplace.util.TipoConfiguracion;
import marketplace.util.TipoUsuarioLogin;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PCADMIN
 */
@Service
public class AutenticacionBmoiServiceImpl implements AutenticacionBmoiService {

    @Autowired
    private CuentasRepository cuentasRepository;
    @Autowired
    private ConfiguracionesRepository configuracionesRepository;
    @Autowired
    private EnviarCorreoService correoService;
    @Autowired
    private Encriptador encriptador;

    @Override
    public AutUsuarioOutDto obtenerUsuario(String idUsuario) throws Exception {
        Integer idUser = encriptador.desencriptarIDString(idUsuario);
        Predicate p = QCuentas.cuentas.idPersona.id.eq(idUser);
        Cuentas cuenta = cuentasRepository.findOne(p).get();
        AutUsuarioOutDto usuarioOutDto = new AutUsuarioOutDto();
        usuarioOutDto.setIdPersona(cuenta.getIdPersona().getId());
        usuarioOutDto.setCorreo(cuenta.getIdPersona().getEmail());
        usuarioOutDto.setIdRol(cuenta.getIdRol().getId());
        usuarioOutDto.setNombres(cuenta.getIdPersona().getNombres() + " " + cuenta.getIdPersona().getPriApellido() + " " + cuenta.getIdPersona().getSegApellido());
        usuarioOutDto.setRol(cuenta.getIdRol().getNombre());
        return usuarioOutDto;
    }

    @Transactional
    @Override
    public void cambiarContraseniaEnviarLink(String correo) throws Exception {
        PersonaCuentaEntity personaCuenta = cuentasRepository.existeCuenta(correo);
        if (personaCuenta == null) {
            throw new ExceptionUser("El e-mail ingresado no existe");
        }
        String key = correo + ";" + LocalDateTime.now().toString() + ";" + TipoUsuarioLogin.BMOI.getValor();
        String encriptado = encriptador.encriptarTexto(key);
        String escapeKey = Util.escapeJavascript(encriptado);
        cuentasRepository.updateKeyNewPassword(personaCuenta.getId(), encriptado);
        correoService.enviarCorreoRecuperarContrasenia(correo, personaCuenta.getNombres(), escapeKey);
    }

    @Override
    public String cambiarContraseniaVerficarKey(KeyInDto keyInDto) throws Exception {
        String unescape = Util.unescapeJavascript(keyInDto.getValor());
        String key = encriptador.desencriptarTexto(unescape);
        String Correo = key.split(";")[0];
        boolean existeCuenta = cuentasRepository.existeKey(Correo, unescape);
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
        Predicate p = QCuentas.cuentas.idPersona.email.eq(Correo);
        Optional<Cuentas> optCuenta = cuentasRepository.findOne(p);
        if (!optCuenta.isPresent()) {
            throw new ExceptionUser("Operación denegada");
        }
        Cuentas cuenta = optCuenta.get();
        String contraseniaEncriptada = encriptador.encriptarTexto(cambiarContraseniaInDto.getContrasenia());
        cuenta.setContrasenia(contraseniaEncriptada);
        cuentasRepository.save(cuenta);
        cuentasRepository.updateKeyNewPassword(cuenta.getIdPersona().getId(), null);
    }

    @Transactional
    @Override
    public void activarCuenta(CambiarContraseniaInDto cambiarContraseniaInDto) throws Exception {
        String unescape = Util.unescapeJavascript(cambiarContraseniaInDto.getKey());
        String key = encriptador.desencriptarTexto(unescape);
        String Correo = key.split(";")[0];
        Predicate p = QCuentas.cuentas.idPersona.email.eq(Correo);
        Optional<Cuentas> optCuenta = cuentasRepository.findOne(p);
        if (!optCuenta.isPresent()) {
            throw new ExceptionUser("Operación denegada");
        }
        Cuentas cuenta = optCuenta.get();
        if (cuenta.getEstado() == EstadoRegistro.ACTIVO.getId()) {
            throw new ExceptionUser("La cuenta ya fue activada");
        }
        String contraseniaEncriptada = encriptador.encriptarTexto(cambiarContraseniaInDto.getContrasenia());
        cuenta.setContrasenia(contraseniaEncriptada);
        cuenta.setEstado(EstadoRegistro.ACTIVO.getId());
        cuentasRepository.save(cuenta);
        cuentasRepository.updateKeyNewPassword(cuenta.getIdPersona().getId(), null);
    }

}
