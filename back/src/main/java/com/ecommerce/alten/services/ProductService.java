package com.ecommerce.alten.services;

import com.ecommerce.alten.models.Product;
import com.ecommerce.alten.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        if (isAdmin()) {
            throw new SecurityException("Unauthorized: Only admin can delete products.");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (isAdmin()) {
            throw new SecurityException("Unauthorized: Only admin can delete products.");
        }
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        if (isAdmin()) {
            throw new SecurityException("Unauthorized: Only admin can delete products.");
        }
        productRepository.deleteById(id);
    }

    private boolean isAdmin() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("le mail "+currentUserEmail);
        return !"admin@admin.com".equals(currentUserEmail);
    }
}
