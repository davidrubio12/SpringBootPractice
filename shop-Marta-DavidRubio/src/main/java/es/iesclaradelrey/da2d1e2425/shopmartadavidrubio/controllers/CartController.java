package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

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
    public String addCart(@ModelAttribute(name = "productId") @PathVariable("productId") Long productId,@RequestParam String returnUrl) {
        cartService.add(productId);
        return "redirect:" + returnUrl;
    }
    @GetMapping("/delete/{id}")
    public String deleteCart(@ModelAttribute(name = "id") @PathVariable("id") Long id,@RequestParam String returnUrl) {

        cartService.deleteById(id);

        ;
        return "redirect:" + returnUrl;
    }



@GetMapping("/deleteAll")
public String deleteAllCarts() {

    cartService.deleteAll();
    return "redirect:/cart/showCart"; }

}
