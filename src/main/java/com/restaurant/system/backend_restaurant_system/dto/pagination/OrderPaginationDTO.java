package com.restaurant.system.backend_restaurant_system.dto.pagination;

import java.util.List;

import com.restaurant.system.backend_restaurant_system.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaginationDTO {

    private int totalPages;
    private long totalElements;
    private int size;
    private List<OrderDTO> results;
    
}
