package marketplace.security.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by stephan on 20.03.16.
 */
@Getter
@Setter
public class JwtUser implements UserDetails {

    private final static String VACIO = "";

    private final String username;
    private final String idUsuario;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Date lastPasswordResetDate;

    public JwtUser(String username, String idUsuario, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.idUsuario = idUsuario;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.authorities = authorities;
        this.enabled = true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
