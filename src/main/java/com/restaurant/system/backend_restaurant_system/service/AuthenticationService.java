package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.AuthenticationRequest;
import com.restaurant.system.backend_restaurant_system.dto.AuthenticationResponseDTO;

import jakarta.validation.Valid;

public interface AuthenticationService {

    AuthenticationResponseDTO login(@Valid AuthenticationRequest authenticationRequest);
    
}
