package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.OrderPaginationDTO;

public interface OrderService {

    OrderPaginationDTO getAllOrder(int page, int size);
    
}
