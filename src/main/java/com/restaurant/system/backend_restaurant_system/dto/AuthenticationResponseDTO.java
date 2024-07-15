package com.restaurant.system.backend_restaurant_system.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponseDTO implements Serializable{
    
    private String message;
    private Boolean access;
    private String rol;
}