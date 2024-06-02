package com.restaurant.system.backend_restaurant_system.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {

    private Long id;
    private String waiter;
    private List<OrderDetailDTO> orderDetails;
    
}
