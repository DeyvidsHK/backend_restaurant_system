package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserDTO;
import com.restaurant.system.backend_restaurant_system.dto.UserPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.User;

public interface UserService {

    UserPaginationDTO getAllUser(int page, int size);

    User createUser(UserDTO userDTO);

    DeleteResponseDTO deleteUserById(Long id);

    void updateUser(Long id, UserDTO userDTO);

    
}
