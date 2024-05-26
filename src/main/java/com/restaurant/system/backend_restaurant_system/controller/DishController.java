package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.DishPaginationDTO;
import com.restaurant.system.backend_restaurant_system.service.DishService;

@RestController
@RequestMapping(path = "/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping(path = "/list")
    public ResponseEntity<DishPaginationDTO> getAllDish(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int siz){
        
        DishPaginationDTO dishPage = dishService.getAllDish(page, siz);
        
        return ResponseEntity.ok().body(dishPage);
    }

}
