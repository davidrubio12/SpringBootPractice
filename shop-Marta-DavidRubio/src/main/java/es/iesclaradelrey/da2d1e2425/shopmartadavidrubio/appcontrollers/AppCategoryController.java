package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CategoryDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/app/v1/categories")
public class AppCategoryController {
    private final CategoryService categoryService ;
    private final ModelMapper modelMapper;
    public AppCategoryController(CategoryService categoryService, ModelMapper modelMapper) {

        this.categoryService = categoryService;
        this.modelMapper = modelMapper;

    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getAllCategories() {
        Collection<CategoryDto> dtos = categoryService.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

}
