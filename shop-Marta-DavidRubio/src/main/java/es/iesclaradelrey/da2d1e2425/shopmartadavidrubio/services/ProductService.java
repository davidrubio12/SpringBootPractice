package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(long id);
    Collection<Product> findByCategoryId(long id);




}
