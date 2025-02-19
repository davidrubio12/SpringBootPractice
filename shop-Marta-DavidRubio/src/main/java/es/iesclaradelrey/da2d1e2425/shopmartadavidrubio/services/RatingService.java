package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingService {
    Optional<Double> findRatingById(Long id);
    Collection<Rating>findAllRatingsByProductIdOrderByDateDesc(Long id);
}
