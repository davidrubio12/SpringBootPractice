package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
    @ModelAttribute("currentUrl")
    public String getCurrentUrl(HttpServletRequest request) {
        return request.getRequestURI().replace(request.getContextPath(),"");
    }
}
