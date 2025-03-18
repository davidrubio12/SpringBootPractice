package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.forms;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/forms")
public class FormNewProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public FormNewProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/newProduct")
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/products/forms/newProductForm");
        System.out.println("Entrando en /forms/newProduct");
        modelAndView.addObject("product", new NewProductDto());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
    @PostMapping("/newProduct")
    public String newProduct(@Valid @ModelAttribute("product") NewProductDto newProduct,BindingResult bindingResult ,Model model ) {

        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });
        System.out.println(newProduct);
        if (bindingResult.hasErrors()) {

//            model.addAttribute("product", newProduct);
            model.addAttribute("categories", categoryService.findAll());
            return "/admin/products/forms/newProductForm";
        }
//    model.addAttribute("product", newProduct);
    model.addAttribute("categories", categoryService.findAll());
    productService.create(newProduct);

    return "/admin/products/forms/newProductForm";

    }

}
