package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    AppUser register(RegisterUserDto registerUserDto);
    Optional<AppUser> findByEmail(String email);

    AppUser save(AppUser user);

    List<AppUser> findAll();
}
