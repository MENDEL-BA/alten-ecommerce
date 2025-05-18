package com.ecommerce.alten.services;

import com.ecommerce.alten.dtos.CartItemDTO;
import com.ecommerce.alten.models.Cart;
import com.ecommerce.alten.models.Product;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.repositories.CartRepository;
import com.ecommerce.alten.repositories.ProductRepository;
import com.ecommerce.alten.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(Long userId, CartItemDTO itemDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        cart.addItem(product, itemDTO.getQuantity());
        return cartRepository.save(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }
}

