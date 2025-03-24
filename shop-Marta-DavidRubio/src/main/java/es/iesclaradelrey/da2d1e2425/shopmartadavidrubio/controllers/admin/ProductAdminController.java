package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.ProductAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductAdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
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

    @GetMapping("/new")
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/products/forms/newProductForm");
        System.out.println("Entrando en /forms/newProduct");
        modelAndView.addObject("product", new NewProductDto());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
    @PostMapping("/new")
    public String newProduct(
            @Valid @ModelAttribute("product") NewProductDto newProduct,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "/admin/products/forms/newProductForm";
        }

        try {
            productService.create(newProduct);
        } catch (ProductAlreadyExistsException e) {
                        bindingResult.reject("globalError", "Error al crear el producto: " + e.getMessage());
            model.addAttribute("categories", categoryService.findAll());
            return "/admin/products/forms/newProductForm";
        } catch (Exception e) {

            bindingResult.reject("globalError", "Error inesperado al crear el producto: " + e.getMessage());
            model.addAttribute("categories", categoryService.findAll());
            return "/admin/products/forms/newProductForm";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Producto creado correctamente");
        return "redirect:/admin/products/list";
    }
}
