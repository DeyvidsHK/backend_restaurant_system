package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.OrderPaginationDTO;
import com.restaurant.system.backend_restaurant_system.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/List")
    @Operation(summary = "Obtener lista de Ordenes")
    public ResponseEntity<OrderPaginationDTO> getAllOrder(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        
        OrderPaginationDTO orderPage = orderService.getAllOrder(page, size);
        
        return ResponseEntity.ok().body(orderPage);
    }
    
}
