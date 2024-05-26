package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.CategoryPaginationDTO;

public interface CategoryService {

    CategoryPaginationDTO getAllCategories(int page, int size);
    
}
