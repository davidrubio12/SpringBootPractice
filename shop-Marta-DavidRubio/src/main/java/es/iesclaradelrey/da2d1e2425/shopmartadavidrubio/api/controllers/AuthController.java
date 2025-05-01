package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.api.controllers;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.TokensDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AppUserService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AppUserService appUserService;
    private final JwtService jwtService;

    public AuthController(AppUserService appUserService, JwtService jwtService) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokensDto> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        AppUser appUser = appUserService.register(registerUserDto);
        String accessToken = jwtService.generateAccessToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        return ResponseEntity.ok(TokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());




    }

    @PostMapping("/login")
    public ResponseEntity<TokensDto> login(@RequestBody LoginUserDto loginUserDto){

        AppUser appUser = appUserService.login(loginUserDto);

        String accessToken = jwtService.generateAccessToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        return ResponseEntity.ok(TokensDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokensDto> refresh(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @PostMapping("/revoque")
    public ResponseEntity<Void> revoke(){
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
