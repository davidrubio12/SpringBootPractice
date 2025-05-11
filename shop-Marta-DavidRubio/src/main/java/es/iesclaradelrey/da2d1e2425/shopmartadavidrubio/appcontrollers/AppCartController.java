package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.appcontrollers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/v1/cart")
public class AppCartController {

    private final CartService cartService;

    public AppCartController(CartService cartService) {
        this.cartService = cartService;
    }

    //permitir que cada usuario vea,
    // modifique y vacíe su propio carrito, no el de otros.
    @GetMapping
    public ResponseEntity<CartDto> getCart() {
        CartDto cart = cartService.getCartForCurrentUser();
        return ResponseEntity.ok(cart);
    }

}
