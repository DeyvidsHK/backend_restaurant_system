package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.pagination.RoomPaginationDTO;
import com.restaurant.system.backend_restaurant_system.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/Room")
@Tag(name = "Room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/List")
    @Operation(summary = "Obtener lista de pisos")
    public ResponseEntity<RoomPaginationDTO> getAllRoom(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        
        RoomPaginationDTO roomPage = roomService.getAllRoom(page, size);

        return ResponseEntity.ok().body(roomPage);
    }
    
}
