package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.dto.api.CartItemDto;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Cart;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.exceptions.ProblemDetailException;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.CartRepository;
import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> ProblemDetailException.notFound(
                        "No existe el producto con código: " + id,
                        "https://api.magicemporium.com/errors/product-not-found"
                ));

        Cart cart = cartRepository.findByProductId(id).orElse(new Cart(0, product));
        cart.setUser(user);
        cart.setQuantity(cart.getQuantity() + quantity);

        if (cart.getQuantity() > product.getStockQuantity()) {
            throw ProblemDetailException.badRequest(
                    "No hay suficientes unidades. En total solo se pueden pedir: " + product.getStockQuantity(),
                    "https://api.magicemporium.com/errors/not-enough-stock"
            );
        }

        cart.setModified(LocalDateTime.now());
        cartRepository.save(cart);
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByEmail(email)
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



    @Override
    public CartDto addProductToCart(Long productId, int quantity) {
        if (quantity <= 0) {
            throw ProblemDetailException.badRequest(
                    "La cantidad debe ser mayor que 0.",
                    "https://api.magicemporium.com/errors/invalid-quantity"
            );
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ProblemDetailException.notFound(
                        "Producto no encontrado",
                        "https://api.magicemporium.com/errors/product-not-found"
                ));

        Cart cartLine = cartRepository.findByUserAndProduct(user, product)
                .orElse(new Cart(0, product));

        cartLine.setUser(user);
        cartLine.setProduct(product);
        cartLine.setQuantity(cartLine.getQuantity() + quantity);

        if (cartLine.getQuantity() > product.getStockQuantity()) {
            throw ProblemDetailException.badRequest(
                    "No hay suficientes unidades. Solo hay " + product.getStockQuantity(),
                    "https://api.magicemporium.com/errors/not-enough-stock"
            );
        }

        cartLine.setModified(LocalDateTime.now());
        cartRepository.save(cartLine);

        return getCartForCurrentUser();
    }


    @Override
    public CartDto addOneProductToCart(Long productId) {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ProblemDetailException.notFound(
                        "Producto no encontrado",
                        "https://api.magicemporium.com/errors/product-not-found"
                ));

        Cart cartLine = cartRepository.findByUserAndProduct(user, product)
                .orElse(new Cart(0, product));

        cartLine.setUser(user);
        cartLine.setProduct(product);
        cartLine.setQuantity(cartLine.getQuantity() + 1);

        if (cartLine.getQuantity() > product.getStockQuantity()) {
            throw ProblemDetailException.badRequest(
                    "No hay suficientes unidades. Solo hay " + product.getStockQuantity(),
                    "https://api.magicemporium.com/errors/not-enough-stock"
            );
        }

        cartLine.setModified(LocalDateTime.now());
        cartRepository.save(cartLine);

        return getCartForCurrentUser();
    }

    @Transactional
    @Override
    public CartDto removeProductFromCart(Long productId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                throw ProblemDetailException.unauthorized(
                        "Usuario no autenticado",
                        "https://api.magicemporium.com/errors/unauthorized"
                );
            }

            String username = authentication.getName();
            AppUser user = appUserRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> ProblemDetailException.notFound(
                            "Producto no encontrado",
                            "https://api.magicemporium.com/errors/product-not-found"
                    ));

            cartRepository.deleteByUserAndProduct(user, product);

            return getCartForCurrentUser();

        } catch (Exception e) {

            throw ProblemDetailException.internalServerError(
                    "Error eliminando producto del carrito: " + e.getMessage(),
                    "https://api.magicemporium.com/errors/internal-server-error"
            );
        }
    }

    @Override
    public CartDto clearCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<Cart> cartItems = cartRepository.findByUser(user);
        cartRepository.deleteAll(cartItems);

        return getCartForCurrentUser();
    }







}
