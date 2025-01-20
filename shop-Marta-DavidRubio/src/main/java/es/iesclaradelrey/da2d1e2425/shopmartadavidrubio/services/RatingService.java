package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;

import java.util.Collection;

public interface RatingService {
    Collection<Product> findRatingById(Long id);
}
