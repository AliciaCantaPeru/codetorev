package marketplace.security;

import java.io.IOException;
import static java.util.Optional.ofNullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import static org.springframework.util.StringUtils.isEmpty;
import org.springframework.web.filter.OncePerRequestFilter;
import marketplace.security.util.JwtTokenUtil;

@Log4j2
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Value("${security.url-permit-all:/**}")
    private String permitAll;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String headerAut = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (!isEmpty(headerAut) && headerAut.startsWith("Bearer ")) {
                final String authToken = headerAut.replace("Bearer ", "");
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (!jwtTokenUtil.validateToken(authToken, userDetails)) {
                    String refreshToken = jwtTokenUtil.refreshToken(authToken);
                    response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    response.addHeader(HttpHeaders.AUTHORIZATION, refreshToken);
                }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, ofNullable(userDetails), userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "La sesión expiró");
        }
    }
}
