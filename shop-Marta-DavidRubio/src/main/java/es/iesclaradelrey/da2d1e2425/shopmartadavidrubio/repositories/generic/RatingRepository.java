package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RatingRepository extends JpaRepository<Product, Long>
{
    Collection<Product> findRatingById(Long id);


}
