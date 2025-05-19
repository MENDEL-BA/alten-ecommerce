package com.ecommerce.alten;

import com.ecommerce.alten.models.Product;
import com.ecommerce.alten.models.User;
import com.ecommerce.alten.repositories.ProductRepository;
import com.ecommerce.alten.repositories.UserRepository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.core.StreamWriteConstraints;
@SpringBootApplication
public class AltenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltenApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = JsonMapper.builder()
				.build();
		mapper.getFactory().setStreamWriteConstraints(
				StreamWriteConstraints.builder().maxNestingDepth(2000).build()
		);
		mapper.registerModule(new JavaTimeModule());
		return mapper;
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {
			UserRepository userRepository = ctx.getBean(UserRepository.class);
			PasswordEncoder passwordEncoder = ctx.getBean(PasswordEncoder.class);

			User user = new User();
			User useradmin = new User();
			user.setUsername("testuser");
			user.setFirstname("Test");
			user.setEmail("testuser@example.com");
			user.setPassword(passwordEncoder.encode("password123"));
			useradmin.setUsername("testadmin");
			useradmin.setFirstname("Admin");
			useradmin.setEmail("admin@admin.com");
			useradmin.setPassword(passwordEncoder.encode("admin123"));
			userRepository.save(user);
			userRepository.save(useradmin);

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
