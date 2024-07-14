package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.ServiceTableDTO;

public interface TableService {

    ServiceTableDTO getTableByIdRoom(Long id);
  
}
