package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;


    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String pageCategories(@RequestParam(defaultValue = "1") Integer pageNumber,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        @RequestParam(defaultValue = "name")String orderBy,
                        @RequestParam(defaultValue = "asc") String orderDir,
                        Model model) {

        Map<String,String> orderFields=new LinkedHashMap<>();
        orderFields.put("name", "Nombre");
        orderFields.put("description", "Descripcion");
        orderFields.put("id", "Id");
        model.addAttribute("orderFields", orderFields);

        model.addAttribute("categories", categoryService.findAll(pageNumber, pageSize, orderBy, orderDir));
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderDir", orderDir);


        return "admin/categories/list";
    }
}
