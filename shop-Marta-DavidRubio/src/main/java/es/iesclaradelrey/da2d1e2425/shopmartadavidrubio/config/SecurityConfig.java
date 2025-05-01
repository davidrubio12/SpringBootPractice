package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.config;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.filters.JwtAuthenticationFilter;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        //Desactivar CSRF
        http.csrf(config->
                config //TODO esto también mirar everything lo de las URL
                        .ignoringRequestMatchers("/api/**")
                        //.ignoringRequestMatchers("/api/v1/auth/login")

                //Evitar los problemas de autentificación con los formularios.
                // Así lo desactivamos en ciertas rutas

        );
        http
                .authorizeHttpRequests(auth ->
                        auth    //TODO revisar esto de las URL
                               // .requestMatchers("/api/v1/tasks/**").authenticated()
                                .requestMatchers("home/app/api/v1/auth/**").permitAll()
                                .anyRequest().permitAll()
                        );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
