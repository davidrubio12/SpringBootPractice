package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(999)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // De momento, permitir el acceso a cualquier parte de la aplicación.
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll());
        return http.build();
    }
}
