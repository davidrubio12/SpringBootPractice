package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProblemDetailException extends RuntimeException {
    public ProblemDetailException(HttpStatus status, String detail) {
        super();
    }

    public static ProblemDetailException badRequest(String detail) {
        return new ProblemDetailException(HttpStatus.BAD_REQUEST, detail);
    }

    public static ProblemDetailException notFound(String detail) {
        return new ProblemDetailException(HttpStatus.NOT_FOUND, detail);
    }

    public static ProblemDetailException unauthorized(String detail) {
        return new ProblemDetailException(HttpStatus.UNAUTHORIZED, detail);
    }

    public static ProblemDetailException internalServerError(String detail) {
        return new ProblemDetailException(HttpStatus.INTERNAL_SERVER_ERROR, detail);
    }

}
