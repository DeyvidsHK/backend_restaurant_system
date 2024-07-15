package com.restaurant.system.backend_restaurant_system.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {
    
    private String email;

    private String password;

}
