/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.util.impl;

import java.util.HashMap;
import java.util.Map;
import marketplace.repository.ConfiguracionesRepository;
import marketplace.service.util.EnviarCorreoService;
import marketplace.support.EnviarCorreoSupport;
import marketplace.support.dto.CorreoInfo;
import marketplace.util.JsonConversor;
import marketplace.util.TipoConfiguracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Service
public class EnviarCorreoServiceImpl implements EnviarCorreoService {

    private final String LINK = "[LINK]";
    private final String MINUTOS = "[MINUTOS]";

    @Autowired
    private ConfiguracionesRepository configuracionesRepository;

    @Autowired
    private EnviarCorreoSupport correoSupport;

    @Value("${url-new-password:}")
    private String urlRecuperar;

    @Value("${url-activar-cuenta:}")
    private String urlActivarCuenta;

    @Override
    @Async
    public void enviarCorreoRecuperarContrasenia(String correoDestino, String nombrePersona, String link) throws Exception {
        CorreoInfo correoInfo = new CorreoInfo();
        String correoConf = configuracionesRepository.obtenerValor(TipoConfiguracion.CONF_CORREO.getValor());
        CorreoInfo.CorreoConf conf = JsonConversor.fromStringToObject(correoConf, CorreoInfo.CorreoConf.class);
        String contenido = configuracionesRepository.obtenerValor(TipoConfiguracion.CONTENIDO_RECUPERAR_CONTRASEÑA.getValor());
        String asunto = configuracionesRepository.obtenerValor(TipoConfiguracion.ASUNTO_RECUPERAR_CONTRASEÑA.getValor());
        String tecaWeb = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_WEB.getValor());
        String tecaRedSocial = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_REDES_SOCIALES.getValor());
        String tecaCelular = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_CELULAR_CONTACTO.getValor());
        String tecaTelefono = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_TELEFONO_CONTACTO.getValor());
        String tecaContacto = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_CONTACTO.getValor());
        String facebook = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_FACEBOOK.getValor());
        String instagram = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_INSTAGRAM.getValor());
        String linkedin = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_LINKEDIN.getValor());
        String twitter = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_TWITTER.getValor());
        String wasap = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_WASSAP.getValor());
        String youtube = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_YOUTUBE.getValor());

        String tiempoExpiracion = configuracionesRepository.obtenerValor(TipoConfiguracion.TIEMPO_EXPIRACION_CORREO.getValor());
        String logo = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_LOGO.getValor());
        correoInfo.setCorreoConf(conf);
        correoInfo.setAsunto(asunto);
        correoInfo.setContenido(contenido);
        correoInfo.setCorreoDestino(correoDestino);
        Map<String, String> map = new HashMap<>();
        map.put(TipoConfiguracion.BMOI_LOGO.getValor(), logo);
        map.put(TipoConfiguracion.BMOI_WEB.getValor(), tecaWeb);
        map.put(TipoConfiguracion.BMOI_REDES_SOCIALES.getValor(), tecaRedSocial);
        map.put(TipoConfiguracion.BMOI_CELULAR_CONTACTO.getValor(), tecaCelular);
        map.put(TipoConfiguracion.BMOI_TELEFONO_CONTACTO.getValor(), tecaTelefono);
        map.put(TipoConfiguracion.BMOI_CONTACTO.getValor(), tecaContacto);
        map.put(TipoConfiguracion.BMOI_FACEBOOK.getValor(), facebook);
        map.put(TipoConfiguracion.BMOI_INSTAGRAM.getValor(), instagram);
        map.put(TipoConfiguracion.BMOI_LINKEDIN.getValor(), linkedin);
        map.put(TipoConfiguracion.BMOI_TWITTER.getValor(), twitter);
        map.put(TipoConfiguracion.BMOI_WASSAP.getValor(), wasap);
        map.put(TipoConfiguracion.BMOI_YOUTUBE.getValor(), youtube);

        map.put(MINUTOS, tiempoExpiracion);
        map.put(LINK, urlRecuperar + link);
        correoInfo.setParametros(map);
        correoSupport.enviarCorreo(correoInfo);
    }

    @Override
    @Async
    public void enviarCorreoActivacionCuenta(String correoDestino, String nombrePersona, String passTemporal, String link) throws Exception {
        CorreoInfo correoInfo = new CorreoInfo();
        String correoConf = configuracionesRepository.obtenerValor(TipoConfiguracion.CONF_CORREO.getValor());
        CorreoInfo.CorreoConf conf = JsonConversor.fromStringToObject(correoConf, CorreoInfo.CorreoConf.class);
        String contenido = configuracionesRepository.obtenerValor(TipoConfiguracion.CONTENIDO_CREAR_CUENTA.getValor());
        String asunto = configuracionesRepository.obtenerValor(TipoConfiguracion.ASUNTO_CREAR_CUENTA.getValor());
        String tecaWeb = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_WEB.getValor());
        String tecaRedSocial = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_REDES_SOCIALES.getValor());
        String tecaCelular = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_CELULAR_CONTACTO.getValor());
        String tecaTelefono = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_TELEFONO_CONTACTO.getValor());
        String tecaContacto = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_CONTACTO.getValor());
        String facebook = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_FACEBOOK.getValor());
        String instagram = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_INSTAGRAM.getValor());
        String linkedin = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_LINKEDIN.getValor());
        String twitter = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_TWITTER.getValor());
        String wasap = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_WASSAP.getValor());
        String youtube = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_YOUTUBE.getValor());

        String tiempoExpiracion = configuracionesRepository.obtenerValor(TipoConfiguracion.TIEMPO_EXPIRACION_CORREO.getValor());
        String logo = configuracionesRepository.obtenerValor(TipoConfiguracion.BMOI_LOGO.getValor());
        correoInfo.setCorreoConf(conf);
        correoInfo.setAsunto(asunto);
        correoInfo.setContenido(contenido);
        correoInfo.setCorreoDestino(correoDestino);
        Map<String, String> map = new HashMap<>();
        map.put(TipoConfiguracion.BMOI_LOGO.getValor(), logo);
        map.put(TipoConfiguracion.BMOI_WEB.getValor(), tecaWeb);
        map.put(TipoConfiguracion.BMOI_REDES_SOCIALES.getValor(), tecaRedSocial);
        map.put(TipoConfiguracion.BMOI_CELULAR_CONTACTO.getValor(), tecaCelular);
        map.put(TipoConfiguracion.BMOI_TELEFONO_CONTACTO.getValor(), tecaTelefono);
        map.put(TipoConfiguracion.BMOI_CONTACTO.getValor(), tecaContacto);
        map.put(TipoConfiguracion.BMOI_FACEBOOK.getValor(), facebook);
        map.put(TipoConfiguracion.BMOI_INSTAGRAM.getValor(), instagram);
        map.put(TipoConfiguracion.BMOI_LINKEDIN.getValor(), linkedin);
        map.put(TipoConfiguracion.BMOI_TWITTER.getValor(), twitter);
        map.put(TipoConfiguracion.BMOI_WASSAP.getValor(), wasap);
        map.put(TipoConfiguracion.BMOI_YOUTUBE.getValor(), youtube);

        map.put(MINUTOS, tiempoExpiracion);
        map.put(LINK, urlActivarCuenta + link);
        correoInfo.setParametros(map);
        correoSupport.enviarCorreo(correoInfo);
    }

}
