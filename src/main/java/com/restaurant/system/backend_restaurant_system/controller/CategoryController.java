package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.CategoryPaginationDTO;
import com.restaurant.system.backend_restaurant_system.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/Category")
public class CategoryController {
    

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/List")
    @Operation(summary = "Obtener lista de categorias")
    public ResponseEntity<CategoryPaginationDTO> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        
        CategoryPaginationDTO categoriesPage = categoryService.getAllCategories(page, size);
        
        return ResponseEntity.ok().body(categoriesPage);
    }


}
