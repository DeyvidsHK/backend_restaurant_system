package com.restaurant.system.backend_restaurant_system.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    
    private Long user_id;

    private List<CreateOrderDetailDTO> orderDetail;

}
