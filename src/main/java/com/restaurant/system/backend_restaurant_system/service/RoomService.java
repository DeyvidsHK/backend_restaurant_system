package com.restaurant.system.backend_restaurant_system.service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.RoomDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.RoomPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Room;

public interface RoomService {
    
    RoomPaginationDTO getAllRoom(int page, int size);

    Room createRoom(RoomDTO roomDTO);

    DeleteResponseDTO deleteRoomById(Long id);

    void updateRoom(Long id, RoomDTO roomDTO);

}
