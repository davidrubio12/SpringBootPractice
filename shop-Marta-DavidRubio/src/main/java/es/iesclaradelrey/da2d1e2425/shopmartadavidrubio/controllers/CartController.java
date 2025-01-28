package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.controllers;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.CartService;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;



    public CartController(CartService cartService) {
        this.cartService = cartService;

    }
    @GetMapping("/showCart")
    public ModelAndView showCart() {
        ModelAndView mav = new ModelAndView("cart");

        mav.addObject("cartDetails", cartService.findAll());
        return mav;
    }
    @GetMapping("add_cart/{productId}")
    public String addCart(@ModelAttribute(name = "productId") @PathVariable("productId") Long productId) {

       String url = "javascript:history.back();";
        cartService.add(productId);
        return "redirect:" + url;
    }



}
