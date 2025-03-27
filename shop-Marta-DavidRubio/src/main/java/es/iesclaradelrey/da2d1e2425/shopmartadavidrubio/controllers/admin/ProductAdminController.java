package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers.admin;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewCategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.admin.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryAlreadyExistsException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.admin.CategoryNotEmptyException;
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
import java.util.Random;

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
        model.addAttribute("pageSizeOptions", List.of(5, 10, 20));

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
        model.addAttribute("categories", categoryService.findAll());
        if (bindingResult.hasErrors()) {

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


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        try {
            Product product = productService.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado "));

            NewProductDto productDto = new NewProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCategoryId(product.getCategory().getId());

            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("product", productDto);
            model.addAttribute("productId", id);

        } catch (ProductNotFoundException e) {
            model.addAttribute("globalError", e.getMessage());
            return "redirect:/admin/products/list";
        }

        return "admin/products/forms/editProductForm";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("product") NewProductDto updatedProduct,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        bindingResult.getAllErrors().forEach(error -> {
            System.out.println(error.getDefaultMessage());
        });

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productId", id);
        if (bindingResult.hasErrors()) {

            return "admin/products/forms/editProductForm";
        }

        try {
            if (new Random().nextInt(100) > 70) {
                throw new RuntimeException("Error aleaterio");
            }
            ;
            productService.update(id, updatedProduct);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría actualizada correctamente");
            return "redirect:/admin/products/list";

        } catch (ProductNotFoundException e) {
            bindingResult.reject("globalError", e.getMessage());
        } catch (ProductAlreadyExistsException e) {
            bindingResult.rejectValue("name", "product.name", e.getMessage());
        } catch (CategoryNotFoundException e) {
            bindingResult.rejectValue("categoryId", "product.categoryId", e.getMessage());
        } catch (Exception e) {
            bindingResult.reject("globalError", "Error inesperado al actualizar el producto: " + e.getMessage());
        }

        return "admin/products/forms/editProductForm";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            var product = productService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrada"));
            model.addAttribute("product", product);
        } catch (Exception e) {
            model.addAttribute("globalError", "No se pudo cargar el producto para eliminar.");
            return "redirect:/admin/product/list";
        }
        return "admin/products/forms/deleteProductForm";
    }



    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Producto eliminada correctamente.");
        } catch (CategoryNotFoundException | CategoryNotEmptyException e) {
            redirectAttributes.addFlashAttribute("globalError", e.getMessage());
        }
        return "redirect:/admin/products/list";
    }


}

