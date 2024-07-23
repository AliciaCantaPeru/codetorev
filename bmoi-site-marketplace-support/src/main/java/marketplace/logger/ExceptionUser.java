/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.logger;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Martin Pilar <mpilarcastillejo@gmail.com>
 */
@Getter
@Setter
public class ExceptionUser extends RuntimeException {

    private String mensaje;
    private String mensajeDev;

    public ExceptionUser(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    public ExceptionUser(String mensaje, String mensajeDev) {
        super();
        this.mensaje = mensaje;
        this.mensajeDev = mensajeDev;
    }

    @Override
    public String getMessage() {
        if (this.mensaje != null) {
            return mensaje;
        }
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMessageDev() {
        if (this.mensaje != null) {
            return mensajeDev;
        }
        return null;
    }
}
