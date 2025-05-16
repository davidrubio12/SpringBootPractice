package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

public class ProblemDetailException extends RuntimeException {
    private final ProblemDetail problemDetail;

    public ProblemDetailException(HttpStatus status, String detail, String type) {
        super(detail);
        this.problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        this.problemDetail.setTitle(status.getReasonPhrase());
        this.problemDetail.setType(URI.create(type));
    }

    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }

    // Métodos de fábrica estáticos
    public static ProblemDetailException badRequest(String detail, String type) {
        return new ProblemDetailException(HttpStatus.BAD_REQUEST, detail, type);
    }

    public static ProblemDetailException notFound(String detail, String type) {
        return new ProblemDetailException(HttpStatus.NOT_FOUND, detail, type);
    }

    public static ProblemDetailException unauthorized(String detail, String type) {
        return new ProblemDetailException(HttpStatus.UNAUTHORIZED, detail, type);
    }

    public static ProblemDetailException internalServerError(String detail, String type) {
        return new ProblemDetailException(HttpStatus.INTERNAL_SERVER_ERROR, detail, type);
    }
}
