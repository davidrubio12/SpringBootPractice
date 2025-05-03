package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin;

public class UserNameAlreadyExistsException extends RuntimeException {
    public UserNameAlreadyExistsException(String message) {
        super(String.format("Email %s already exists", message));
    }
}
