package com.ecommerce.alten.services;

import com.ecommerce.alten.dtos.WishlistItemDTO;
import com.ecommerce.alten.models.Product;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.models.Wishlist;
import com.ecommerce.alten.repositories.ProductRepository;
import com.ecommerce.alten.repositories.UserRepository;
import com.ecommerce.alten.repositories.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Wishlist addToWishlist(Long userId, WishlistItemDTO itemDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setUser(user);
                    return wishlistRepository.save(newWishlist);
                });

        wishlist.addProduct(product);
        return wishlistRepository.save(wishlist);
    }

    public Wishlist getWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));
    }
}
