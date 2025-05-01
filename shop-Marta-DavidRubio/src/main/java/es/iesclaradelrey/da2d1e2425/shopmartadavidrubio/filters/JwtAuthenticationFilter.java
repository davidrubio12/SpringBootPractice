package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.filters;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //los filtros se ejecutan para todas las peticiones, asiq delimitar

        //extraer q petición me estan haciendo.

        String pathRequest = request.getRequestURI();
        if(pathRequest.contains("/api/v1/tasks")){//mirar nuestra URL.Todo lo que queramos q este protegido
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }
            // extraer el token
            String token = authHeader.substring(7);
            //Validar el token
            try{
                jwtService.validateAccessToken(token);

                String username = jwtService.extractUsername(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null);

                SecurityContextHolder.getContext().setAuthentication(authentication);


            }catch (Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //TODO : mirar si es así -> response.getWriter().println(e.getMessage());
                return;
            }


        }

        filterChain.doFilter(request, response);

    }
}
