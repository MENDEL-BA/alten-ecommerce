package com.ecommerce.alten.controllers;

import com.ecommerce.alten.dtos.CartItemDTO;
import com.ecommerce.alten.models.Cart;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody CartItemDTO itemDTO) {
        System.out.println("user "+user);
        Cart cart = cartService.addToCart(user.getId(), itemDTO);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal User user) {
        Cart cart = cartService.getCart(user.getId());
        return ResponseEntity.ok(cart);
    }
}