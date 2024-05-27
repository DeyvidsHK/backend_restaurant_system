package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    
}
