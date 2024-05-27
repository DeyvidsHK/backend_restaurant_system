package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.UserPaginationDTO;

public interface UserService {

    UserPaginationDTO getAllUser(int page, int size);

    
}
