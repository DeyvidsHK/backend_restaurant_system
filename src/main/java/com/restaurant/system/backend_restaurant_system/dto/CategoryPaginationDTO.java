package com.restaurant.system.backend_restaurant_system.dto;

import java.util.List;

import com.restaurant.system.backend_restaurant_system.persistence.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryPaginationDTO {
    
    private int totalPages;
    private long totalElements;
    private int size;
    private List<Category> results;

}
