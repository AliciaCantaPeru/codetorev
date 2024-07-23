/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.impl;

import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import marketplace.support.EnviarCorreoSupport;
import marketplace.support.dto.CorreoInfo;

@Component
@Log4j2
public class EnviarCorreoSupportImpl implements EnviarCorreoSupport {

    @Override
    @Async
    public void enviarCorreo(CorreoInfo correoInfo) throws Exception {
        try {
            String correoOrigen = correoInfo.getCorreoConf().getCorreo();
            String contraseniaOrigen = correoInfo.getCorreoConf().getClave();
            String tipoContenido = correoInfo.getCorreoConf().getTipoContenido();
            String contenido = correoInfo.getContenido();
            String asunto = correoInfo.getAsunto();
            String correoDestino = correoInfo.getCorreoDestino();
            Session session = getSession(correoInfo.getCorreoConf(), correoOrigen, contraseniaOrigen);

            if (correoInfo.getParametros() != null) {
                for (Map.Entry<String, String> entry : correoInfo.getParametros().entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    contenido = contenido.replace(key, value);
                }
            }

            BodyPart cuerpo = new MimeBodyPart();
            cuerpo.setContent(contenido, tipoContenido);

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(cuerpo);
            if (correoInfo.getArchivosAdjuntos() != null) {
                for (CorreoInfo.ArchivoAdjunto archivo : correoInfo.getArchivosAdjuntos()) {
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(archivo.getFile(), "application/octet-stream"));
                    adjunto.setFileName(archivo.getNombre());
                    multiParte.addBodyPart(adjunto);
                }
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoOrigen));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            message.setSubject(asunto, "UTF-8");
            message.setContent(multiParte);
            Transport.send(message);
            log.info("Correo: enviado a:" + correoDestino);
        } catch (MessagingException e) {
            log.info("error enviarcorreo", e);
        }
    }

    private Session getSession(CorreoInfo.CorreoConf correoConf, String correoOrigen, String clave) {
        Properties props = new Properties();
        correoConf.getPropertys().stream().forEach((property) -> {
            props.put(property.getKey(), property.getValue());
        });
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String desde = correoOrigen;
                String clav = clave;
                return new PasswordAuthentication(desde, clav);
            }

        });
        return session;
    }

}
