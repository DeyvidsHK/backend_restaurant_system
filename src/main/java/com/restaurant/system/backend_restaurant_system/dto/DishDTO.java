package com.restaurant.system.backend_restaurant_system.dto;

import lombok.Data;

@Data
public class DishDTO {
    
    private String code;
    private String name;
    private Double price;
    private Long categoryId;

}
