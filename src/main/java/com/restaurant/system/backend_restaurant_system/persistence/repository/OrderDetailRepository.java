package com.restaurant.system.backend_restaurant_system.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.system.backend_restaurant_system.persistence.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    
    List<OrderDetail> findByOrderId(Long orderId);

}
