package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.CartRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.generic.ProductRepository;
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
    public Optional<Cart> findById(long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void add(Long id) {
        Optional<Cart> cart = cartRepository.findProductById(id);
        if (cart.isPresent()) {
           Cart car = cart.get();
           car.setQuantity(car.getQuantity() + 1);
           car.setModified(LocalDateTime.now());
           cartRepository.save(car);
        }else {
            Product product = productRepository.findById(id).orElse(null);
            Cart cart1 = new Cart();
            cart1.setProduct(product);
            cart1.setQuantity(1);
            cart1.setModified(LocalDateTime.now());
            cart1.setDate(LocalDateTime.now());
            assert product != null;
            cart1.setProductName(product.getName());
            cart1.setPrice(product.getPrice());
            cart1.setImageUrl(product.getImageUrl());
            cartRepository.save(cart1);
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
