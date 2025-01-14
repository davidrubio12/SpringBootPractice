package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    Collection<Product> findByCategoryId(Long id);
}
