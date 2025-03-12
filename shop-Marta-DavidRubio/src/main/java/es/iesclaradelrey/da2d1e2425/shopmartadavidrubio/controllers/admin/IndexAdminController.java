package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @GetMapping({"/", ""})
    public String index(Model model) {

        return "admin/indexAdmin";
    }
}
