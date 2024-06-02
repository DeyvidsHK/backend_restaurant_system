package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.OrderDTO;
import com.restaurant.system.backend_restaurant_system.dto.OrderDetailDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.OrderPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Order;
import com.restaurant.system.backend_restaurant_system.persistence.entity.OrderDetail;
import com.restaurant.system.backend_restaurant_system.persistence.repository.OrderDetailRepository;
import com.restaurant.system.backend_restaurant_system.persistence.repository.OrderRepository;
import com.restaurant.system.backend_restaurant_system.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderPaginationDTO getAllOrder(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Order> orderPage = orderRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = orderPage.getContent().stream()
                .map(order -> mapOrderToDTO(order))
                .collect(Collectors.toList());

        return new OrderPaginationDTO(orderPage.getTotalPages(), orderPage.getTotalElements(), orderPage.getSize(), orderDTOList);
    }

    public OrderDTO mapOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());

        orderDTO.setId(order.getId());
        orderDTO.setWaiter(order.getUser().getName());
        
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream()
            .map(orderDetail -> mapOrderDetailToDTO(orderDetail))
            .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetailDTOs);
        return orderDTO;
    }

    private OrderDetailDTO mapOrderDetailToDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setDish(orderDetail.getDish().getName());
        orderDetailDTO.setPrice(orderDetail.getDish().getPrice());
        
        return orderDetailDTO;
    }

    
}
