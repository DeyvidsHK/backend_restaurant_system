package com.restaurant.system.backend_restaurant_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.RoomDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.RoomPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Room;
import com.restaurant.system.backend_restaurant_system.persistence.repository.RoomRepository;
import com.restaurant.system.backend_restaurant_system.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomPaginationDTO getAllRoom(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<Room> roomPage = roomRepository.findAll(pageable);
        
        return new RoomPaginationDTO(roomPage.getTotalPages(), roomPage.getTotalElements(), roomPage.getSize(), roomPage.getContent());
    }

    @Override
    public Room createRoom(RoomDTO roomDTO) {
        Room newRoom = new Room();
        newRoom.setName(roomDTO.getName());
        newRoom.setCapacity(roomDTO.getCapacity());
        newRoom.setState(true);
    
        Room savedRoom = roomRepository.save(newRoom);
    
        return savedRoom;
    }
    
    
}
