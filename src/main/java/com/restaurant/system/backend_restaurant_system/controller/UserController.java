package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.User;
import com.restaurant.system.backend_restaurant_system.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(path = "/User")
@Tag(name = "User")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(path = "/List")
    @Operation(summary = "Obtener lista de usuarios")
    public ResponseEntity<UserPaginationDTO> getAllUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        
        UserPaginationDTO userPage = userService.getAllUser(page, size);

        return ResponseEntity.ok().body(userPage);
    }

    @PostMapping("/Create")
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(summary = "Eliminar un usuario por su ID")
    public ResponseEntity<DeleteResponseDTO> deleteUserById(@PathVariable Long id) {
        DeleteResponseDTO response = userService.deleteUserById(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
    
    @PutMapping("/Update/{id}")
    public ResponseEntity<MessageDTO> updateUserById(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(id, userDTO);
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.OK.value());
            response.setMessage("Usuario actualizada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
