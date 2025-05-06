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

    @GetMapping("/find")
    public ResponseEntity<Page<Product>> find(@RequestParam String product){

        Page <Product> products = productService.findAll(1,10,"id","asc");

        return ResponseEntity.ok(products);
    }
}
