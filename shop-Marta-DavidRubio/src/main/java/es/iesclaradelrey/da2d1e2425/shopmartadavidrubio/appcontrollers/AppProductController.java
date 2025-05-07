package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.ProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.OperationNotSupportedException;


@RestController
@RequestMapping("/api/app/v1/products")
public class AppProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;


    public AppProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    //permitir a la app Android buscar productos de forma paginada,
    // filtrada por texto y por categoría,
    // y ordenada por distintos atributos (como precio o nombre).
    @GetMapping("/find")
    public ResponseEntity<Page<ProductDto>> find(@RequestParam(defaultValue = "") String search,
                                                 @RequestParam(required = false) Long cat,
                                                 @RequestParam(defaultValue = "name") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String sortDir,
                                                 @RequestParam(defaultValue = "0") int pageNumber,
                                                 @RequestParam(defaultValue = "10") int pageSize){

        Page<Product> products = productService.findWithFilters(search, cat, sortBy, sortDir, pageNumber, pageSize);
        Page<ProductDto> result = products.map(product -> modelMapper.map(product, ProductDto.class));
        return ResponseEntity.ok(result);
    }
}
