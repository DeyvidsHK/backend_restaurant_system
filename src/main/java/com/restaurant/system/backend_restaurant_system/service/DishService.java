package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.DishPaginationDTO;

public interface DishService {
    
    DishPaginationDTO getAllDish(int page, int size);

}