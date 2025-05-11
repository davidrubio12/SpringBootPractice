package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/v1/cart")
public class AppCartController {

    private final CartService cartService;

    public AppCartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<CartDto> getCart() {
        CartDto cart = cartService.getCartForCurrentUser();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{productId}")
    public CartDto addOneProductToCart(@PathVariable Long productId) {
        return cartService.addOneProductToCart(productId);
    }

    @PostMapping("/{productId}/{quantity}")
    public CartDto addProductToCart(@PathVariable Long productId, @PathVariable int quantity) {
        return cartService.addProductToCart(productId, quantity);
    }
    @DeleteMapping("/{productId}")
    public CartDto removeProductFromCart(@PathVariable Long productId) {
        return cartService.removeProductFromCart(productId);
    }
    @DeleteMapping
    public CartDto clearCart() {
        return cartService.clearCart();
    }





}
