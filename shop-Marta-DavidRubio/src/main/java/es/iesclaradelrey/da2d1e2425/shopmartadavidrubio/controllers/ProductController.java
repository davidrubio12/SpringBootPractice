package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{categoryId}")
    public ModelAndView productsByCategory(@PathVariable("categoryId") Long id) {
        ModelAndView mav = new ModelAndView("products");

        Category category = categoryService.findById(id).orElseThrow();
        mav.addObject("category", category);
        Collection<Product> products = productService.findByCategoryId(id);
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("/{productId}")

    public ModelAndView productDetails(@PathVariable("productId") Long id){
        ModelAndView mav = new ModelAndView("productDetails");
        Product products = productService.findById(id).orElseThrow();
        mav.addObject("product", products);

        return mav;
    }








}
