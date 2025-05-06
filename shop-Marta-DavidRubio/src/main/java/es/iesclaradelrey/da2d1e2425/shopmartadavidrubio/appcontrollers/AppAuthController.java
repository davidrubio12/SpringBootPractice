package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.JwtTokensDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AppUserService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AuthService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/v1/auth")

public class AppAuthController {
//    Crear un controlador "AuthController", con los métodos (de momento vacíos)
//
//    Register. Este método necesita:
//    DTO para recibir datos (usuario y contraseña, y otros datos necesarios para la clase de usuario definida)
//    DTO para responder (con token de acceso y token de refresco)
//    Login. Este método necesita:
//    DTO para recibir datos (usuario y contraseña)
//    DTO para responder (con token de acceso y token de refresco)
//    RefreshToken. Este método necesita:
//    DTO para responder, con un nuevo token de acceso (y opcionalmente puede añadirse de refresco)
//    No necesita DTO de entrada.
//    Puede que sea interesante implementar un método revoque(). No recibiría nada, pero revocaría el token recibido.

    private final AuthService authService;


    public AppAuthController(AppUserService appUserService, JwtService jwtService, AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtTokensDto> register(@RequestBody RegisterUserDto registerUserDto){
        return ResponseEntity.ok(authService.register(registerUserDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtTokensDto> login(@RequestBody LoginUserDto loginUserDto){
        return ResponseEntity.ok(authService.login(loginUserDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtTokensDto> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(authService.refresh(authHeader));
    }

    @PostMapping("/revoque")
    public ResponseEntity<Void> revoke(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
