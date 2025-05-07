package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);

    boolean existsByEmail(String email);
}
