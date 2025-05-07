package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartItemDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.NotEnoughQuantityException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CartRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, AppUserRepository appUserRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public Collection<Cart> findAll() {
        return cartRepository.findAll();
    }


    @Override
    public void add(Long id, int quantity) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No existe el producto con código: "+id));
        Cart cart= cartRepository
                .findByProductId(id)
                .orElse(new Cart(0, product));

        cart.setQuantity(cart.getQuantity() + quantity);



        if(cart.getQuantity() > product.getStockQuantity()) {

            throw new NotEnoughQuantityException("No hay suficientes unidades. En total solo se pueden pedir: " + product.getStockQuantity()+ " unidades");//hacer nuestra excepción
        }



        cartRepository.save(cart);

//        Optional<Cart> cart = cartRepository.findByProductId(id);
//
//        if (cart.get().getProduct().getId() == null) {//si el producto NO exite, 404
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con código: " + id);
//        } else {//si existe
//            if (quantity > cart.get().getProduct().getStockQuantity()) {//y lo añadido es mayor al stock, 409
//                ResponseEntity.status(HttpStatus.CONFLICT).body("No hay suficientes unidades. Solo hay " + cart.get().getProduct().getStockQuantity() + " unidades en stock.");
//            } else {//si lo añadido es menor o igual al stock
//
//                if (cart.isPresent()) {//si existe en el carro
//                    System.out.println("Añadiendo unidad a producto existente " + id);
//                    Cart car = cart.get();
//                    car.setQuantity(car.getQuantity() + 1);//¿podemos poner quantity?
//                    car.setModified(LocalDateTime.now());
//                    cartRepository.save(car);
//
//                } else { //si no existe en el carro y hay suficiente stok, añade nuevo producto
//                    System.out.println("Añadiendo nuevo producto " + id);
//                    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el producto " + id));
//                    Cart newCart = new Cart();
//                    newCart.setProduct(product);
//                    newCart.setQuantity(1);
//                    newCart.setModified(LocalDateTime.now());
//                    newCart.setDate(LocalDateTime.now());
//                    newCart.setProductName(product.getName());
//                    newCart.setPrice(product.getPrice());
//                    newCart.setImageUrl(product.getImageUrl());
//                    cartRepository.save(newCart);
//                }
//
//            }
//        }
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
        System.out.println("Eliminando fila de carro " + id);
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }



    @Override
    public CartDto getCartForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<Cart> cartItems = cartRepository.findByUser(user);

        List<CartItemDto> items = cartItems.stream().map(item -> {
            CartItemDto dto = new CartItemDto();
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setImageUrl(item.getProduct().getImageUrl());
            dto.setUnitPrice(item.getProduct().getPrice());
            dto.setQuantity(item.getQuantity());
            dto.setSubtotal(item.getQuantity() * item.getProduct().getPrice());
            return dto;
        }).toList();

        CartDto cartDto = new CartDto();
        cartDto.setItems(items);
        cartDto.setTotalQuantity(items.stream().mapToInt(CartItemDto::getQuantity).sum());
        cartDto.setTotalPrice(items.stream().mapToDouble(CartItemDto::getSubtotal).sum());

        return cartDto;
    }


    @Override
    public Optional<AppUser> findByUsername(String username) {
        return Optional.empty();
    }


}
