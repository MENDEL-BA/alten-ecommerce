package com.ecommerce.alten;

import com.ecommerce.alten.models.Product;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.repositories.ProductRepository;
import com.ecommerce.alten.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@SpringBootApplication
public class AltenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltenApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {
	        User user = new User();
	        user.setUsername("testuser");
	        user.setFirstname("Test");
	        user.setEmail("testuser@example.com");
			PasswordEncoder passwordEncoder = ctx.getBean(PasswordEncoder.class);
			user.setPassword(passwordEncoder.encode("password123"));
	        UserRepository userRepository = ctx.getBean(UserRepository.class);
	        userRepository.save(user);

	        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
	        for (int i = 1; i <= 7; i++) {
	            Product product = new Product();
	            product.setCode("P" + i);
	            product.setName("Product " + i);
	            product.setDescription("Description for Product " + i);
	            product.setImage("image" + i + ".jpg");
	            product.setCategory("Category " + i);
	            product.setPrice(10.0 * i);
	            product.setQuantity(100 - (i * 10));
	            product.setInternalReference("REF" + i);
	            product.setShellId(i);
	            product.setInventoryStatus(i % 3 == 0 ? "OUTOFSTOCK" : (i % 2 == 0 ? "LOWSTOCK" : "INSTOCK"));
	            product.setRating(4);
	            product.setCreatedAt(LocalDateTime.now());
	            productRepository.save(product);
	        }
	    };
	}
}
