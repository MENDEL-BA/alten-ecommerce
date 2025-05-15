package com.ecommerce.alten.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private String username;
    private String firstname;
    private String role;

}