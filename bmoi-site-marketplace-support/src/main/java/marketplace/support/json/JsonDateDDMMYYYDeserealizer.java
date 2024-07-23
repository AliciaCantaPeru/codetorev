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
 * Created by Jimmy Huacho
 */
public class JsonDateDDMMYYYDeserealizer extends JsonDeserializer<Date> {

    private final SimpleDateFormat formatterFull = new SimpleDateFormat("dd/MM/yyyy");

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
