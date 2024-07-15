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
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.CategoryPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Category;
import com.restaurant.system.backend_restaurant_system.dto.CategoryDTO;
import com.restaurant.system.backend_restaurant_system.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "/Category")
@Tag(name = "Category")
public class CategoryController {
    

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/List")
    @Operation(summary = "Obtener lista de categorias")
    public ResponseEntity<CategoryPaginationDTO> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        
        CategoryPaginationDTO categoriesPage = categoryService.getAllCategories(page, size);
        
        return ResponseEntity.ok().body(categoriesPage);
    }

    @PostMapping("/Create")
    @Operation(summary = "Crear una nueva categoria")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(summary = "Eliminar una categoría por su ID")
    public ResponseEntity<DeleteResponseDTO> deleteCategoryById(@PathVariable Long id) {
        DeleteResponseDTO response = categoryService.deleteCategoryById(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/Update/{id}")
    @Operation(summary = "Actualizar una categoría por su ID")
    public ResponseEntity<MessageDTO> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.updateCategory(id, categoryDTO);
            MessageDTO response = new MessageDTO();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Categoría actualizada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            MessageDTO response = new MessageDTO();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}