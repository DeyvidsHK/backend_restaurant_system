package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.pagination.DishPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;
import com.restaurant.system.backend_restaurant_system.persistence.repository.DishRepository;
import com.restaurant.system.backend_restaurant_system.service.DishService;

@Service
public class DishServiceImpl implements DishService{

    @Autowired
    private DishRepository dishRepository;

    @Override
    public DishPaginationDTO getAllDish(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Dish> dishPage = dishRepository.findAll(pageable);
        
        return new DishPaginationDTO(dishPage.getTotalPages(), dishPage.getTotalElements(), dishPage.getSize(), dishPage.getContent());
    }

    
}
