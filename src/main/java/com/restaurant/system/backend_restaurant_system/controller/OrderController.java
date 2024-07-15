package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.CreateOrderDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.OrderPaginationDTO;
import com.restaurant.system.backend_restaurant_system.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/Order")
@Tag(name = "Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/List")
    @Operation(summary = "Obtener lista de Ordenes")
    public ResponseEntity<OrderPaginationDTO> getAllOrder(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        
        OrderPaginationDTO orderPage = orderService.getAllOrder(page, size);
        
        return ResponseEntity.ok().body(orderPage);
    }

    @PostMapping(path = "/Create")
    @Operation(summary = "Crear orden")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO createOrderDTO){

        // Validar si user_id está presente y no es nulo
        if (createOrderDTO.getUser_id() == null || createOrderDTO.getUser_id() == 0) {
            return ResponseEntity.badRequest().body("Se requiere identificación de usuario.");
        }

        // Validar si orderDetail está presente y no está vacío
        if (createOrderDTO.getOrderDetail() == null || createOrderDTO.getOrderDetail().isEmpty()) {
            return ResponseEntity.badRequest().body("Se requieren detalles del pedido.");
        }

        MessageDTO response = orderService.createOrder(createOrderDTO);
        
        return ResponseEntity.status(response.getStatus()).body(response);
    } 
    
}
