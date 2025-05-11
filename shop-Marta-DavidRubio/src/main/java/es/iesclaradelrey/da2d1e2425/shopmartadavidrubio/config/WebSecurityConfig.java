package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.config;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(999)
public class WebSecurityConfig {

//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthService authService) throws Exception {
//        //TODO para poder añadirlo al carro, evita añadir cookies de csrf
//        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/add-to-cart"));
//        // De momento, permitir el acceso a cualquier parte de la aplicación.
//        http.authorizeHttpRequests(auth -> auth
//                // .anyRequest().permitAll()); // permitir el acceso a cualquier parte de la aplicación.
//                .requestMatchers("/login-personalizado").permitAll()
//                .anyRequest().authenticated());
//
//        http.formLogin(login->
//                login
//                    .loginPage("/login-personalizado")
//        );
//
//
//        return http.build();
//    }
}
