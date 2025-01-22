package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingService {
    Collection<Rating> findAllRatings(Long id);
    Optional<Double> findRatingById(Long id);
}
