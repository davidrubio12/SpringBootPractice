package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String pagesProducts(@RequestParam(defaultValue = "1") Integer pageNumber,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        @RequestParam(defaultValue = "name") String orderBy,
                        @RequestParam(defaultValue = "asc") String orderDir,
                        Model model) {

        Map<String, String> orderFields = new LinkedHashMap<>();
        orderFields.put("name", "Nombre");
        orderFields.put("description", "Descripcion");
        orderFields.put("id", "Id");
        model.addAttribute("orderFields", orderFields);

        model.addAttribute("products", productService.findAll(pageNumber, pageSize, orderBy, orderDir));
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);
        model.addAttribute("pageSizeOptions", List.of(5,10,20));

        return "admin/products/list";
    }
}
