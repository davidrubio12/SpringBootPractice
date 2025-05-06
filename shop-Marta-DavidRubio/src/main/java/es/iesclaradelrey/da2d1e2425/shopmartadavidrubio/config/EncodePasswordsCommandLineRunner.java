package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.config;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EncodePasswordsCommandLineRunner implements CommandLineRunner {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EncodePasswordsCommandLineRunner(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Obtener todos los usuarios
        List<AppUser> allUsers = appUserService.findAll();

        // Iterar sobre cada usuario
        for (AppUser appUser : allUsers) {
            String oldPassword = appUser.getPassword();

            // Verificar si la contraseña ya está cifrada con bcrypt
            if (!maybeBcryptHash(oldPassword)) {
                // Si no está cifrada, ciframos la contraseña
                appUser.setPassword(passwordEncoder.encode(oldPassword));
                // Guardamos el usuario con la nueva contraseña cifrada
                appUserService.save(appUser);
            }
        }
    }

    // Método para verificar si una contraseña ya está cifrada con bcrypt
    private boolean maybeBcryptHash(String password) {
        // La expresión regular asegura que la contraseña esté cifrada con bcrypt (formato $2a$)
        return password != null && password.matches("^\\$2[aby]?\\$\\d{2}\\$.{53}$");
    }
}

