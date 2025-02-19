package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProductId(long id);
}
