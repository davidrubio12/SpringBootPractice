package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCategoryId(Long id);
    Optional<Product> findById(Long id);

    boolean existsByName(String name);
    boolean existsByCategoryId(Long id);

    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategory_Id(String name, String description, Long categoryId, Pageable pageable);
}
