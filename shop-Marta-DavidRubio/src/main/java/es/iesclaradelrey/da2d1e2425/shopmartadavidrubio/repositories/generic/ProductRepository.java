package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCategoryId(Long id);
    Optional<Product> findById(Long id);

}
