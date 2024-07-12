package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.CategoryDTO;
import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.CategoryPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Category;

public interface CategoryService {

    CategoryPaginationDTO getAllCategories(int page, int size);

    Category createCategory(CategoryDTO categoryDTO);

    DeleteResponseDTO  deleteCategoryById(Long id);

    void updateCategory(Long id, CategoryDTO categoryDTO);
    
}
