package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.CartRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Collection<Cart> findAll() {
        return cartRepository.findAll();
    }


    @Override
    public void add(Long id) {
        Optional<Cart> cart = cartRepository.findByProductId(id);
        if (cart.isPresent()) {
            System.out.println("Añadiendo unidad a producto existente " + id);
           Cart car = cart.get();
           car.setQuantity(car.getQuantity() + 1);
           car.setModified(LocalDateTime.now());
           cartRepository.save(car);
        }else {
            System.out.println("Añadiendo nuevo producto " + id);
            Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el producto " + id));
            Cart newCart = new Cart();
            newCart.setProduct(product);
            newCart.setQuantity(1);
            newCart.setModified(LocalDateTime.now());
            newCart.setDate(LocalDateTime.now());
            newCart.setProductName(product.getName());
            newCart.setPrice(product.getPrice());
            newCart.setImageUrl(product.getImageUrl());
            cartRepository.save(newCart);
        }
    }

    @Override
    public Double countCart() {
        double total = 0.0;
    for (Cart cart : cartRepository.findAll()) {
        total += cart.getProduct().getPrice() * cart.getQuantity();
    }
        return total;
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }

}
