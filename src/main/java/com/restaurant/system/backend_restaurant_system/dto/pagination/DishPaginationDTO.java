package com.restaurant.system.backend_restaurant_system.dto.pagination;

import java.util.List;

import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishPaginationDTO {

    private int totalPages;
    private long totalElements;
    private int size;
    private List<Dish> results;
    
}
