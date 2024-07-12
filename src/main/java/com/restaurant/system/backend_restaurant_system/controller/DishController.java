package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.DishDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.DishPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Dish;
import com.restaurant.system.backend_restaurant_system.service.DishService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "/Dish")
@Tag(name = "Dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping(path = "/List")
    @Operation(summary = "Obtener lista de platos")
    public ResponseEntity<DishPaginationDTO> getAllDish(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        
        DishPaginationDTO dishPage = dishService.getAllDish(page, size);
        
        return ResponseEntity.ok().body(dishPage);
    }

    @PostMapping(path = "/Create")
    @Operation(summary = "Crear un nuevo plato")
    public ResponseEntity<Object> createDish(@RequestBody DishDTO dishDTO) {
        try {
            Dish createdDish = dishService.createDish(dishDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(summary = "Eliminar un plato por su ID")
    public ResponseEntity<DeleteResponseDTO> deleteDishById(@PathVariable Long id) {
        DeleteResponseDTO response = dishService.deleteDishById(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<MessageDTO> updateDishById(@PathVariable Long id, @RequestBody DishDTO DishDTO) {
        try {
            dishService.updateDish(id, DishDTO);
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.OK.value());
            response.setMessage("Plato actualizado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
