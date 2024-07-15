package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.AuthenticationRequest;
import com.restaurant.system.backend_restaurant_system.dto.AuthenticationResponseDTO;
import com.restaurant.system.backend_restaurant_system.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/authenticate")
    @Operation(summary = "Login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        
        AuthenticationResponseDTO response = authenticationService.login(authenticationRequest);
    
        return ResponseEntity.ok(response);
    }

}
