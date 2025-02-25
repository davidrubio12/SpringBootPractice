package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.NewProductDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public void ProductDtoToProuct(NewProductDto newProductDto) {
        Product product = new Product();

        product.setName(newProductDto.getName());
        product.setDescription(newProductDto.getDescripcion());
        product.setPrice(newProductDto.getPrice());
        product.setStockQuantity(newProductDto.getStockQuantity());
        product.setCategory(categoryRepository.getReferenceById(newProductDto.getCategoryId()));
        productRepository.save(product);
    }


}
