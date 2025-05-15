package com.ecommerce.alten.repositories;


import com.ecommerce.alten.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}