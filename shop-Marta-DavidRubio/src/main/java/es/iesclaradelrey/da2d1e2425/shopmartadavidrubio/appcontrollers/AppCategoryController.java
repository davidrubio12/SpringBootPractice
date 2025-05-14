package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;


@RestController
@RequestMapping("/api/app/v1/categories")
public class AppCategoryController {
    private final CategoryService categoryService ;
    public AppCategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;

    }

    @GetMapping
    public ResponseEntity<Collection<Category>> getAllCategories() {
        Collection<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

}
