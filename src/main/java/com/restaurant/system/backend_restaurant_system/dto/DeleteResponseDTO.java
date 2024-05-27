package com.restaurant.system.backend_restaurant_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponseDTO {
    
    private boolean success;
    private String message;
    private String exceptionMessage;

}
