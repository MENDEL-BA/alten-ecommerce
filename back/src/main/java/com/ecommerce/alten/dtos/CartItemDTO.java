package com.ecommerce.alten.dtos;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private int quantity;
}
