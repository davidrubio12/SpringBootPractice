package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Product, Long>
{@Query(value = "select avg(rating_number) from rating where product_id = :id",nativeQuery = true)
    Optional<Double> findRatingById(@Param("id") Long id);


}
