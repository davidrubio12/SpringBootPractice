package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping({"", "/"})
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/",""})
    public ModelAndView getAllCategories() {
        Collection<Category> categories = categoryService.findAll();
        System.out.println(categories);
        return new ModelAndView("home", "categories", categories);
    }

    @GetMapping("/login-personalizado")
    public String login() {
        return "login-personalizado";
    }


}
