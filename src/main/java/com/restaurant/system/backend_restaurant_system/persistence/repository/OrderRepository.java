package com.restaurant.system.backend_restaurant_system.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.system.backend_restaurant_system.persistence.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
