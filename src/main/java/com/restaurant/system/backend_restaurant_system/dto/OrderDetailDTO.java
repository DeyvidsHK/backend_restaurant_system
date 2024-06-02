package com.restaurant.system.backend_restaurant_system.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    
    private String dish;

    private Double price;
    
    private Integer quantity;

}
