package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.JwtTokensDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.AppUserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserService appUserService;
    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;


    public AuthServiceImpl(AppUserService appUserService, JwtService jwtService, AuthenticationManager authenticationManager, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public JwtTokensDto register(RegisterUserDto registerUserDto) {
        AppUser appUser = appUserService.register(registerUserDto);

        String accessToken = jwtService.generateAccessToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);



        return JwtTokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public JwtTokensDto login(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword());

        authenticationManager.authenticate(authenticationToken);
        AppUser appUser = appUserService.findByEmail(loginUserDto.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User %s not found", loginUserDto.getEmail())));
        String accessToken = jwtService.generateAccessToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        return JwtTokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtTokensDto refresh(String authHeader) {
        if(authHeader == null||!authHeader.startsWith("Bearer ")){
            throw new JwtException("Authorization header is missing or incorrect");
        }

        String token = authHeader.substring(7);

        jwtService.validateRefreshToken(token);

        String username = jwtService.extractUsername(token);

        AppUser appUser = appUserService.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User %s not found", username)));

        String accessToken = jwtService.generateAccessToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);


        return JwtTokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public long getCurrentAppUserId() {
        return this.getCurrentAppUser().getUserId();
        //TODO mirar a partir de aquí(taskServiceImpl-> lo nuestro será otra cosa)
    }

    @Override
    public AppUser getCurrentAppUser() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //Buscar el usuario en el repositorio
        return appUserRepository.findByEmail(userName).orElseThrow(()-> new UsernameNotFoundException(String.format("User %s not found", userName)));
    }
}
