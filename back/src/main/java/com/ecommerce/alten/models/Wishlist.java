package com.ecommerce.alten.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wishlist_products",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Wishlist addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");

        if (this.products == null) {
            this.products = new HashSet<>();
        }

        this.products.add(product);
        return this; // Permet le method chaining
    }
}