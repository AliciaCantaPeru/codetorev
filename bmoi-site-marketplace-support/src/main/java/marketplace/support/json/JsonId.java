/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import marketplace.util.Encriptador;

/**
 *
 * @author PCADMIN
 */
@Log4j2
public class JsonId {

    private final static Map<Integer, String> map_des_enc__numero = new HashMap<>();
    private final static Map<String, Integer> map_enc_dec__numero = new HashMap<>();

    public static class JsonIdIntegerDeserializer extends JsonDeserializer<Integer> {

        private final Encriptador encriptador = new Encriptador();

        @Override
        public Integer deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

            try {
                String idUsuario_encriptado = jp.getText();

                if (idUsuario_encriptado == null) {
                    return null;
                }
                Integer idUsuario = map_enc_dec__numero.get(idUsuario_encriptado);

                if (idUsuario == null) {
                    idUsuario = Integer.valueOf(encriptador.desencriptarID(idUsuario_encriptado));
                    map_enc_dec__numero.put(idUsuario_encriptado, idUsuario);
                    map_des_enc__numero.put(idUsuario, idUsuario_encriptado);
                }

                return idUsuario;

            } catch (IOException | NumberFormatException exception) {
                log.error(exception.getMessage());
                throw new IOException(exception);
            }

        }

    }

    public static class JsonIdIntegerSerializer extends JsonSerializer<Integer> {

        private final Encriptador encriptador = new Encriptador();

        @Override
        public void serialize(Integer t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
            String idUsuario_encriptado = map_des_enc__numero.get(t);
            if (idUsuario_encriptado == null) {
                idUsuario_encriptado = encriptador.encriptarID(t.toString());
                map_des_enc__numero.put(t, idUsuario_encriptado);
                map_enc_dec__numero.put(idUsuario_encriptado, t);
            }
            jg.writeString(idUsuario_encriptado);
        }

    }

    public static class JsonIdStringDeserializer extends JsonDeserializer<String> {

        private final Encriptador encriptador = new Encriptador();

        @Override
        public String deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
            try {
                String idUsuario_encriptado = jp.getText();
                if (idUsuario_encriptado == null) {
                    return null;
//                    idUsuario_encriptado = "";
                }
                String idUsuario = encriptador.desencriptarID(idUsuario_encriptado);
                return String.valueOf(idUsuario);
            } catch (Exception exception) {
                log.error(exception.getMessage());
                throw new IOException(exception);
            }
        }

    }

    public static class JsonIdStringSerializer extends JsonSerializer<String> {

        private final Encriptador encriptador = new Encriptador();

        @Override
        public void serialize(String t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
            jg.writeString(encriptador.encriptarID(t));
        }

    }
}
