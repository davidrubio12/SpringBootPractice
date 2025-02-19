package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long>
{@Query(value = "select avg(rating_number) from rating where product_id = :id",nativeQuery = true)
    Optional<Double> findRatingById(@Param("id") Long id);


   Collection<Rating>findByProductIdOrderByDateDesc(Long id);
}
