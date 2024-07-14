package com.restaurant.system.backend_restaurant_system.dto;

import java.util.List;

import com.restaurant.system.backend_restaurant_system.persistence.entity.ServiceTable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceTableDTO {
    
    private String msg;

    private Integer status;

    private List<ServiceTable> serviceTable;

}
