package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.AuthenticationRequest;
import com.restaurant.system.backend_restaurant_system.dto.AuthenticationResponseDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.User;
import com.restaurant.system.backend_restaurant_system.persistence.repository.UserRepository;
import com.restaurant.system.backend_restaurant_system.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequest authenticationRequest) {
        
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        
        Optional<User> findUser = userRepository.findByEmail(authenticationRequest.getEmail());

        if (findUser.isPresent()) {
            User user = findUser.get();
            if (user.getPassword().equals(authenticationRequest.getPassword())) {
                authResponse.setAccess(true);
                authResponse.setRol(user.getRole().toString());
                authResponse.setMessage("Login exitoso");
            } else {
                authResponse.setAccess(false);
                authResponse.setRol(null);
                authResponse.setMessage("Credenciales inválidas.");
            }
        } else {
            authResponse.setAccess(false);
            authResponse.setRol(null);
            authResponse.setMessage("Usuario no encontrado");
        }

        return authResponse;
    }
    
}
