package marketplace.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private final static long serialVersionUID = -8970718410437077606L;

    private final static String NO_AUTORIZADO = "{ \"tipo\": 2,  \"mensaje\": \"Sin autorizacion para acceder al recurso\",\"mensajeDev\": \"[MENSAJE_DEV]\"}";

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        // Se invoca cuando el usuario intenta acceder a un recurso REST seguro sin proporcionar credenciales
        // Solo deberíamos enviar una respuesta 401 no autorizada porque no hay 'página de inicio de sesión' para redirigir a  
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, NO_AUTORIZADO);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(NO_AUTORIZADO.replace("[MENSAJE_DEV]", authException.getMessage()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        out.flush();
    }

}
