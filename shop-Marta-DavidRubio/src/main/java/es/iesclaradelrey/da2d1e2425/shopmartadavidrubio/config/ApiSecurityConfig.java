package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.config;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(3)
public class ApiSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        //el método "securityMatcher", que hace que la cadena generada solo aplique en cierta ruta.
        http.securityMatcher("/api/app/**");

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(config -> config
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);

        http.httpBasic(AbstractHttpConfigurer::disable);

        //el método "authorizeHttpRequests" para diferenciar los dos tipos de servicios, los de autorización (no autenticados) y los de tareas (autenticados).

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/app/v1/auth/**").permitAll()

                .anyRequest().authenticated());
        //Añade el filtro de autorización que procesará las cabeceras de las peticiones para buscar el token JWT y validarlo.

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}
