package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import java.util.Collection;


public interface CartService {

    Collection<Cart> findAll();

    void add(Long id, int quantity);

    Double countCart();

    void deleteById(Long id);

    void deleteAll();
}


