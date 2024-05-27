package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.DishDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.DishPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Category;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;
import com.restaurant.system.backend_restaurant_system.persistence.repository.CategoryRepository;
import com.restaurant.system.backend_restaurant_system.persistence.repository.DishRepository;
import com.restaurant.system.backend_restaurant_system.service.DishService;

@Service
public class DishServiceImpl implements DishService{

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public DishPaginationDTO getAllDish(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Dish> dishPage = dishRepository.findAll(pageable);
        
        return new DishPaginationDTO(dishPage.getTotalPages(), dishPage.getTotalElements(), dishPage.getSize(), dishPage.getContent());
    }

    @Override
    public Dish createDish(DishDTO dishDTO) {
        Dish newDish = new Dish();
        newDish.setCode(dishDTO.getCode());
        newDish.setName(dishDTO.getName());
        newDish.setPrice(dishDTO.getPrice());

        // Obtener la categoría por su ID
        Category category = categoryRepository.findById(dishDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        newDish.setCategory(category);

        return dishRepository.save(newDish);
    }

    @Override
    public DeleteResponseDTO deleteDishById(Long id) {
        
        DeleteResponseDTO response = new DeleteResponseDTO();
        Optional<Dish> dishOptional = dishRepository.findById(id);

        if (dishOptional.isPresent()) {
            dishRepository.deleteById(id);
            response.setSuccess(true);
            response.setMessage("Plato eliminada exitosamente");
            
        } else {
            response.setSuccess(false);
            response.setMessage("No se encontró el plato con el ID especificado");
        }

        return response;
    }

    
}
