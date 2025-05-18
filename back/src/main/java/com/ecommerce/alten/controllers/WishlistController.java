package com.ecommerce.alten.controllers;

import com.ecommerce.alten.dtos.WishlistItemDTO;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.models.Wishlist;
import com.ecommerce.alten.services.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addToWishlist(
            @AuthenticationPrincipal User user,
            @RequestBody WishlistItemDTO itemDTO) {
        Wishlist wishlist = wishlistService.addToWishlist(user.getId(), itemDTO);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping
    public ResponseEntity<Wishlist> getWishlist(@AuthenticationPrincipal User user) {
        Wishlist wishlist = wishlistService.getWishlist(user.getId());
        return ResponseEntity.ok(wishlist);
    }
}