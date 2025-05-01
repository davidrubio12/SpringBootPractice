package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.JwtTokensDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;

public interface AuthService {

    JwtTokensDto register(RegisterUserDto registerUserDto);

    JwtTokensDto login(LoginUserDto loginUserDto);
    JwtTokensDto refresh(String authHeader);
}
