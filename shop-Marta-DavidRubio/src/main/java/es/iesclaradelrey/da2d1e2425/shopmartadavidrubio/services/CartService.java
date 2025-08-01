package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import java.util.Collection;
import java.util.Optional;


public interface CartService {

    Collection<Cart> findAll();

    void add(Long id, int quantity);

    Double countCart();

    void deleteById(Long id);

    void deleteAll();
    CartDto addProductToCart(Long productId, int count);
    CartDto addOneProductToCart(Long productId);

    CartDto getCartForCurrentUser();
    CartDto removeProductFromCart(Long productId);
    CartDto clearCart();


    Optional<AppUser> findByUsername(String username);
}


