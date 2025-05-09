package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.AddToCartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.NotEnoughQuantityException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;

    }


    @GetMapping("/showCart")
    public ModelAndView showCart() {
        ModelAndView mav = new ModelAndView("cart");

        mav.addObject("cartDetails", cartService.findAll());
        mav.addObject("total", cartService.countCart());
        return mav;
    }

    @GetMapping("/add_cart/{productId}")
    public String addCart( @PathVariable("productId") Long productId, @RequestParam String returnUrl) {
        cartService.add(productId, 1);
        return "forward:" + returnUrl;
    }



    @GetMapping("/deleteAll")
    public String deleteAllCarts() {

        cartService.deleteAll();
        return "forward:/cart/showCart";
    }

    @GetMapping("/deleteOneProduct/{id}")
    public String deleteOneProduct( @PathVariable("id") Long cartId, @RequestParam String returnUrl) {
        cartService.deleteById(cartId);
        return "forward:"+returnUrl;

    }

    @PostMapping("/api/add-to-cart")
    @ResponseBody
    public ResponseEntity<String> addToCartViaApi(@RequestBody AddToCartDto addToCartDto) {
        try {
            cartService.add(addToCartDto.getProductId(), addToCartDto.getQuantity());
            return ResponseEntity.ok("Producto añadido correctamente al carrito.");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotEnoughQuantityException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }


}
