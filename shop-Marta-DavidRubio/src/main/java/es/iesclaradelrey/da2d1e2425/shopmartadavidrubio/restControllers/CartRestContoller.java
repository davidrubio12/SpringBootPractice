package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.restControllers;


import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.AddProductToCartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartRestContoller {

    private final CartService cartService;

    public CartRestContoller(CartService cartService) {

        this.cartService = cartService;
    }

    //Método para añadir producto al carro. mirar si existe, si no exixte devuelve 404
    // + mensaje “No existe el producto con código <código>”

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestBody AddProductToCartDto addProductToCartDto)  {

        cartService.add(addProductToCartDto.getProductId(), addProductToCartDto.getQuantity());
        return ResponseEntity.ok("Producto añadido");//Se ha añadido correctamente




    }

//    @GetMapping
//    public ResponseEntity<Collection<Cart>> getCart() {
//        return ResponseEntity.ok(cartService.findAll());
//    }
}
