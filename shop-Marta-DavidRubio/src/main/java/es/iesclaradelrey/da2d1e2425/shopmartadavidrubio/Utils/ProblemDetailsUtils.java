package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.io.IOException;



import java.net.URI;

public class ProblemDetailsUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

//Error 404
    public static void writeUnauthorized(HttpServletResponse response, HttpServletRequest request, String detail) throws IOException {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setTitle("Unauthorized");
        problem.setDetail(detail);
        problem.setType(URI.create("urn:problem-type:unauthorized"));
        problem.setInstance(URI.create(request.getRequestURI()));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/problem+json");
        objectMapper.writeValue(response.getWriter(), problem);
    }
    //Error 403
    public static void writeForbidden(HttpServletResponse response, HttpServletRequest request, String detail) throws IOException {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problem.setTitle("Forbidden");
        problem.setDetail(detail);
        problem.setType(URI.create("urn:problem-type:forbidden"));
        problem.setInstance(URI.create(request.getRequestURI()));

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/problem+json");
        objectMapper.writeValue(response.getWriter(), problem);
    }

}
