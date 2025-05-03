package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;

public interface JwtService {
    String generateAccessToken(AppUser user);

    String generateRefreshToken(AppUser user);

    void validateAccessToken(String token);

    void validateRefreshToken(String token);

    String extractUsername(String token);
}
