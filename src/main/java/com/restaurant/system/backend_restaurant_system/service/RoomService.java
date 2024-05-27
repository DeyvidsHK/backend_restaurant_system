package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.RoomPaginationDTO;

public interface RoomService {
    
    RoomPaginationDTO getAllRoom(int page, int size);

}
