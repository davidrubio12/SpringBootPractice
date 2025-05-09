package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProductId(long id);


    //Optional<Cart> findByUser(AppUser user);
    List<Cart> findByUser(AppUser user);
    Optional<Cart> findByUserAndProduct(AppUser user, Product product);
    void deleteByUserAndProduct(AppUser user, Product product);




}
