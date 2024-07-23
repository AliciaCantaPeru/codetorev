/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.support.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 *
 * @author gmantilla
 */
public class JsonDateDDMMYYYHHMMSSDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat formatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateAsString = p.getText();
        try {
            if (!StringUtils.hasText(dateAsString)) {
                return null;
            }
            return formatterFull.parse(dateAsString);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
    }

}
