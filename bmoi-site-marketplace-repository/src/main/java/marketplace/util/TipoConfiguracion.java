/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Getter
@AllArgsConstructor
public enum TipoConfiguracion {
    TIEMPO_EXPIRACION_CORREO("TIEMPO_EXPIRACION_CORREO"),
    CONTENIDO_RECUPERAR_CONTRASEÑA("CONTENIDO_RECUPERAR_CONTRASEÑA"),
    ASUNTO_RECUPERAR_CONTRASEÑA("ASUNTO_RECUPERAR_CONTRASEÑA"),
    CONTENIDO_CREAR_CUENTA("CONTENIDO_CREAR_CUENTA"),
    ASUNTO_CREAR_CUENTA("ASUNTO_CREAR_CUENTA"),
    BMOI_WEB("BMOI_WEB"),
    BMOI_REDES_SOCIALES("BMOI_REDES_SOCIALES"),
    BMOI_TELEFONO_CONTACTO("BMOI_TELEFONO_CONTACTO"),
    BMOI_CELULAR_CONTACTO("BMOI_CELULAR_CONTACTO"),
    BMOI_CONTACTO("BMOI_CONTACTO"),
    BMOI_LOGO("BMOI_LOGO"),
    CONF_CORREO("CONF_CORREO"),
    BMOI_FACEBOOK("BMOI_FACEBOOK"),
    BMOI_INSTAGRAM("BMOI_INSTAGRAM"),
    BMOI_LINKEDIN("BMOI_LINKEDIN"),
    BMOI_TWITTER("BMOI_TWITTER"),
    BMOI_WASSAP("BMOI_WASSAP"),
    BMOI_YOUTUBE("BMOI_YOUTUBE"),
    HORAS_MAXIMA_DESCONEXION("HORAS_MAX_DESCONEXION_DEVICE"),
    INTERVALO_MES_VENCIMIENTO("INTERVALO_MES_VENCIMIENTO");

    private final String valor;
}
