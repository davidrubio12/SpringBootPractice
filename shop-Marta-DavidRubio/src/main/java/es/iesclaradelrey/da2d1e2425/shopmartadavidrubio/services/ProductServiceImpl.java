package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
    productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
    productRepository.deleteById(product.getId());
    }

    @Override
    public Collection<Product> findAll() {
    return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {

       return productRepository.findById(id);
    }

    @Override
    public Collection<Product> findByCategoryId(long id) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategoryId() == id)
                .toList();
    }
}