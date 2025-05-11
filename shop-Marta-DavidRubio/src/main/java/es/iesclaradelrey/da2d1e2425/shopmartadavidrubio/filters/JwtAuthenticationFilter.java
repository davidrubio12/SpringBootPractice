package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.filters;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.Utils.ProblemDetailsUtils;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
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
                                    @NonNull  HttpServletResponse response,
                                   @NonNull FilterChain filterChain) throws ServletException, IOException {

        //los filtros se ejecutan para todas las peticiones, asiq delimitar

        //extraer q petición me estan haciendo.

        String pathRequest = request.getRequestURI();

        System.out.println("pathRequest: " + pathRequest);

        //if (request.getServletPath().startsWith("/api/app/v1/auth")) { ??
        if (pathRequest.contains("/api/app/v1/auth")) {

            filterChain.doFilter(request, response);
            return;
        }


        if(pathRequest.contains("/api/app/v1")){//mirar nuestra URL.Todo lo que queramos q este protegido
            // extraer el token
//            String token = authHeader.substring(7);
            //Validar el token
            try{

                String authHeader = request.getHeader("Authorization");

                if(authHeader == null || !authHeader.startsWith("Bearer ")) {

                     throw new JwtException("Authorization header missing or incorrect.");
                }


                String token = authHeader.substring(7);

                String username = jwtService.extractUsername(token);


                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception e){
                //cambiado, limpiamos context y devolvemos error como ProblemDetail
                SecurityContextHolder.clearContext();
                String errorMessage = "Error validating access token: " + e.getMessage();
                ProblemDetailsUtils.writeUnauthorized(response, request, errorMessage);
                return;
            }


        }

        filterChain.doFilter(request, response);

    }
}


