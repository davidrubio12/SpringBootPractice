package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.forms;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forms")
public class FormNewProductController {
    private final CategoryService categoryService;

    public FormNewProductController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "forms/newProductForm";
    }
    @PostMapping("/newProduct")
    public String newProduct(@ModelAttribute NewProductDto newProduct, Model model) {

    model.addAttribute("product", newProduct);
    model.addAttribute("categories", categoryService.findAll());
    return "forms/newProductForm";

    }

}
