package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.CreateOrderDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.OrderPaginationDTO;

public interface OrderService {

    OrderPaginationDTO getAllOrder(int page, int size);

    MessageDTO createOrder(CreateOrderDTO createOrderDTO);
    
}
