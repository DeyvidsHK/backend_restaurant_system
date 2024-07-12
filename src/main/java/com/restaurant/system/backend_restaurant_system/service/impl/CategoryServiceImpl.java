package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.CategoryDTO;
import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.CategoryPaginationDTO;
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

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setCode(categoryDTO.getCode());
        newCategory.setName(categoryDTO.getName());
        newCategory.setDescription(categoryDTO.getDescription());

        Category savedCategory = categoryRepository.save(newCategory);

        return savedCategory;
    }

    @Override
    public DeleteResponseDTO deleteCategoryById(Long id) {
        DeleteResponseDTO response = new DeleteResponseDTO();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            response.setSuccess(true);
            response.setMessage("Categoría eliminada exitosamente");
        } else {
            response.setSuccess(false);
            response.setMessage("No se encontró la categoría con el ID especificado");
        }
        return response;
    }

    @Override
    public void updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new RuntimeException("Categoría no encontrada con el ID: " + id);
        }

        Category existingCategory = optionalCategory.get();
        existingCategory.setCode(categoryDTO.getCode());
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        categoryRepository.save(existingCategory);
    }
    
}
