package com.restaurant.system.backend_restaurant_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailDTO {

    private Long dish_id;

    private Integer quantity;
    
}
