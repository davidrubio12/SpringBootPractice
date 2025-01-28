package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;

import java.util.Collection;
import java.util.Optional;

public interface CartService {

    Collection<Cart> findAll();

    Optional<Cart> findById(long id);
    void add(Long id);
}
