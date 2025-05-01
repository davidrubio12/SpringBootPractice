package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.LoginUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.RegisterUserDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.UserNameAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(RegisterUserDto registerUserDto) {
        if(appUserRepository.existsByEmail(registerUserDto.getEmail())){
           throw new UserNameAlreadyExistsException(registerUserDto.getEmail());
        }

        AppUser appUser = AppUser.builder()
                .email(registerUserDto.getEmail())
                .password(
                 passwordEncoder.encode(registerUserDto.getPassword()))
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .build();

        return appUserRepository.save(appUser);
    }

//    @Override
//    public AppUser login(LoginUserDto loginUserDto){
//        UsernamePasswor
//    }
}
