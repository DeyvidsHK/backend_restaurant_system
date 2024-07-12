package com.restaurant.system.backend_restaurant_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
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

    @Override
    public DeleteResponseDTO deleteRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.delete(optionalRoom.get());
            return new DeleteResponseDTO(true, "Room deleted successfully", null);
        } else {
            return new DeleteResponseDTO(false, "Room not found", null);
        }
    }

    @Override
    public void updateRoom(Long id, RoomDTO roomDTO) {
        
        // Obtener plato por su ID
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) {
            throw new RuntimeException("Mesa no encontrada con el ID: " + id);
        }

        Room existingRoom = optionalRoom.get();
        existingRoom.setName(roomDTO.getName());
        existingRoom.setCapacity(roomDTO.getCapacity());

        roomRepository.save(existingRoom);

    }
    
    
}
