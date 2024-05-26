package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.CategoryPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Category;
import com.restaurant.system.backend_restaurant_system.persistence.repository.CategoryRepository;
import com.restaurant.system.backend_restaurant_system.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryPaginationDTO getAllCategories(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Category> categoriesPage = categoryRepository.findAll(pageable);
        
        return new CategoryPaginationDTO(categoriesPage.getTotalPages(), categoriesPage.getTotalElements(), categoriesPage.getSize(), categoriesPage.getContent());
    }
    
}
