package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.OrderPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Order;
import com.restaurant.system.backend_restaurant_system.persistence.repository.OrderRepository;
import com.restaurant.system.backend_restaurant_system.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderPaginationDTO getAllOrder(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Order> orderPage = orderRepository.findAll(pageable);
        
        return new OrderPaginationDTO(orderPage.getTotalPages(), orderPage.getTotalElements(), orderPage.getSize(), orderPage.getContent());
    }
    
}
