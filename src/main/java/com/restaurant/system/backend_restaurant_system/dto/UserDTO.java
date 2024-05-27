package com.restaurant.system.backend_restaurant_system.dto;

import com.restaurant.system.backend_restaurant_system.persistence.util.Role;

import lombok.Data;

@Data
public class UserDTO {
    
    private String name;

    private String email;
    
    private String password;

    private String phone;

    private Role role;

}
