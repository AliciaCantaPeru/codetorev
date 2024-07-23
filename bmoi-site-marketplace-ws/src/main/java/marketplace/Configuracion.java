/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author PCADMIN
 */
@Configuration
@EnableWebMvc
public class Configuracion implements WebMvcConfigurer {

    @Value("${allowed-origins:}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origin = allowedOrigin.split(",");
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowedMethods("PUT", "GET", "POST", "DELETE");
    }

}
