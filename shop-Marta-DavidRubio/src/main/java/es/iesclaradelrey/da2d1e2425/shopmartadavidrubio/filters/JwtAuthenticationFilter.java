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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private static final String PROTECTED_PATH = "/api/app/v1/cart/**";
    private static final String PROTECTED_PATH_PRODUCTS = "/api/app/v1/product/**";
    private static final String PROTECTED_PATH_CATEGORIES = "/api/app/v1/categories";


    private static final AntPathRequestMatcher protectedPathMatcher = new AntPathRequestMatcher(PROTECTED_PATH);
    private static final AntPathRequestMatcher protectedPathMatcherProduct = new AntPathRequestMatcher(PROTECTED_PATH_PRODUCTS);
    private static final AntPathRequestMatcher protectedPathMatcherCategory = new AntPathRequestMatcher(PROTECTED_PATH_CATEGORIES);


    // Añadimos varias rutas protegidas
//    private static final AntPathRequestMatcher[] protectedMatchers = {
//            new AntPathRequestMatcher("/home/app/api/app/v1/cart/**"),
//            new AntPathRequestMatcher("/home/app/api/app/v1/product/**")
//    };

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

     if(protectedPathMatcher.matches(request) || protectedPathMatcherProduct.matches(request) || protectedPathMatcherCategory.matches(request)) {
         try {

             String authHeader = request.getHeader(AUTH_HEADER);


             if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                 throw new JwtException("Authorization header missing or incorrect.");
             }


             String token = authHeader.substring(7);


             jwtService.validateAccessToken(token);


             String username = jwtService.extractUsername(token);

             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

             Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authentication);
         } catch (Exception e) {

             String errorMessage = String.format("Error validating access token: %s", e.getMessage());
             ProblemDetailsUtils.writeUnauthorized(response, request, errorMessage);
             // Se "corta" la cadena de filtros, al no llamar a filterChain.doFilter
             return;
         }
     }

        filterChain.doFilter(request, response);
    }
}
