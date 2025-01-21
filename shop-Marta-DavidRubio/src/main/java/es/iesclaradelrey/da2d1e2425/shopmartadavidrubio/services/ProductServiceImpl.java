package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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



}
