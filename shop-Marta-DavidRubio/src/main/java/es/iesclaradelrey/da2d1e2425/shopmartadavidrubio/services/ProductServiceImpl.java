package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Optional<Product> findById(long id) {

       return productRepository.findById(id);
    }



    @Override
    public Collection<Product> findByCategoryId(long id) {
//         return productRepository.findAll()
//            .stream()
//             .filter(product -> product.getCategory().getId() == id)
//           .toList();
        return productRepository.findByCategoryId(id);
    }

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }



    @Override
    public Page <Product> findAll(Integer pageNumber, Integer pageSize,String orderBy,String orderDir){
        Sort.Direction direction = orderDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageRequest = PageRequest.of(pageNumber-1, pageSize, Sort.by(direction, orderBy));
        return productRepository.findAll(pageRequest);
    }

    @Override
    public void create(NewProductDto newProductDto) {

        if (newProductDto.getCategoryId() == null) {
            throw new IllegalArgumentException("Category id not found");
        }
        if (newProductDto.getName() == null|| newProductDto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name not found");
        }
        if (newProductDto.getDescription() == null) {
            throw new IllegalArgumentException("Product description not found");
        }
        if (newProductDto.getStockQuantity() == null) {
            throw new IllegalArgumentException("Product stock quantity not found");
        }
        if (newProductDto.getPrice() == null ) {
            throw new IllegalArgumentException("Product price not found");

        }





        Product product = new Product();

        product.setName(newProductDto.getName());
        product.setDescription(newProductDto.getDescription());
        product.setPrice(newProductDto.getPrice());
        product.setStockQuantity(newProductDto.getStockQuantity());

//        product.setCategory(categoryRepository.getReferenceById(newProductDto.getCategoryId()));

        Category category=categoryRepository.findById(newProductDto.getCategoryId()).orElseThrow(EntityNotFoundException::new);
        product.setCategory(category);


        productRepository.save(product);
    }


}
