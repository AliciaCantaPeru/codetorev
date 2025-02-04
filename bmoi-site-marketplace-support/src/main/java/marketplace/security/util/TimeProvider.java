package marketplace.security.util;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * Created by stephan on 04.07.17.
 */
@Component
public class TimeProvider implements Serializable {

    private final static long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
}
