package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.User;
import com.restaurant.system.backend_restaurant_system.persistence.repository.UserRepository;
import com.restaurant.system.backend_restaurant_system.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserPaginationDTO getAllUser(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<User> usersPage = userRepository.findAll(pageable);
        
        return new UserPaginationDTO(usersPage.getTotalPages(), usersPage.getTotalElements(), usersPage.getSize(), usersPage.getContent());
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setPhone(userDTO.getPhone());
        newUser.setRole(userDTO.getRole());
        
        // Lógica adicional, como validaciones o encriptación de contraseñas
    
        return userRepository.save(newUser);
    }

    @Override
    public DeleteResponseDTO deleteUserById(Long id) {
        DeleteResponseDTO response = new DeleteResponseDTO();
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            response.setSuccess(true);
            response.setMessage("Usuario eliminado correctamente");
        } else {
            response.setSuccess(false);
            response.setMessage("Usuario no encontrado");
        }
        return response;
    }
    
    
}
