package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{categoryId}")
    public ModelAndView productsByCategory(@PathVariable("categoryId") Long id) {
        Collection<Product> products = productService.findByCategoryId(id);
        System.out.println(products);
        return new ModelAndView("products", "products", products);
    }







}
