package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
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

}
