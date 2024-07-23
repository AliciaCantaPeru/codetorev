package marketplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String PERMITALL_SPLIT = ",";
    private final static String UL_SWAGGER = "/csrf,/v2/api-docs,/swagger-resources/**,/configuration/ui,/webjars/**,/swagger-ui.html,/swagger-resources,";

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${security.url-permit-all:/**}")
    private String permitAll;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoder.createDelegatingPasswordEncoder();
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // This is the ID we use for encoding.
//        String currentId = "pbkdf2.2018";
//
//        // List of all encoders we support. Old ones still need to be here for rolling updates
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        encoders.put(currentId, new Pbkdf2PasswordEncoder());
//
//        return new DelegatingPasswordEncoder(currentId, encoders);
//    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(false);
        super.configure(web); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if (this.permitAll.isEmpty()) {
            httpSecurity
                    // No necesitamos CSRF porque nuestro token es invulnerable
                    .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // No crear sesi�n
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated();
        } else {
            String urlPermitir = UL_SWAGGER.concat(permitAll);
            String[] urlsPermitAll = urlPermitir.split(PERMITALL_SPLIT);
            httpSecurity
                    // No necesitamos CSRF porque nuestro token es invulnerable
                    .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // No crear sesi�n
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .antMatchers(urlsPermitAll).permitAll()
                    .anyRequest().authenticated();
        }
        // Filtro de seguridad basado en JWT personalizado
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // Deshabilitar el almacenamiento en cach� de p�gina
        httpSecurity.headers().cacheControl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Proporciona el AuthenticationManager predeterminado como Bean
        return super.authenticationManagerBean();
    }

}
