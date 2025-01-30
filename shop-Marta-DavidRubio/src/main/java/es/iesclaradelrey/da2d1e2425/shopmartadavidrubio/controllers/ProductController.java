package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final RatingService ratingService;

    public ProductController(ProductService productService, CategoryService categoryService, RatingService ratingService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.ratingService = ratingService;
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
        Product product = productService.findById(id).orElseThrow();
        mav.addObject("product", product);

        Optional <Double> rating = ratingService.findRatingById(id);

        mav.addObject("averageRating", rating);

//        Double averageRating = rating.orElse(0.0); // Devuelve 0.0 si no hay valoraciones
//        mav.addObject("averageRating", averageRating);
        boolean hasHalfStar = false;
        if (rating.isPresent()) {
            Double averageRating = rating.get();
            hasHalfStar = (int) (averageRating * 10) % 10 >= 5;

        }
        mav.addObject("hasHalfStar", hasHalfStar);


        Collection<Rating> ratings = ratingService.findAllRatingsByProductIdOrderByDateDesc(id);
        mav.addObject("ratings", ratings);

        return mav;
    }

}
