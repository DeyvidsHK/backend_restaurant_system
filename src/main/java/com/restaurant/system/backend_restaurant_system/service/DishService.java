package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.DishDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.DishPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;

public interface DishService {
    
    DishPaginationDTO getAllDish(int page, int size);

    Dish createDish(DishDTO dishDTO);

    DeleteResponseDTO deleteDishById(Long id);

    void updateDish(Long id, DishDTO dishDTO);

}
