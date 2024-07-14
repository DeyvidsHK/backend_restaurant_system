package com.restaurant.system.backend_restaurant_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.system.backend_restaurant_system.dto.DeleteResponseDTO;
import com.restaurant.system.backend_restaurant_system.dto.MessageDTO;
import com.restaurant.system.backend_restaurant_system.dto.RoomDTO;
import com.restaurant.system.backend_restaurant_system.dto.ServiceTableDTO;
import com.restaurant.system.backend_restaurant_system.dto.pagination.RoomPaginationDTO;
import com.restaurant.system.backend_restaurant_system.persistence.entity.Room;
import com.restaurant.system.backend_restaurant_system.service.RoomService;
import com.restaurant.system.backend_restaurant_system.service.TableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/Room")
@Tag(name = "Room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private TableService tableService;

    @GetMapping("/List")
    @Operation(summary = "Obtener lista de pisos")
    public ResponseEntity<RoomPaginationDTO> getAllRoom(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        
        RoomPaginationDTO roomPage = roomService.getAllRoom(page, size);

        return ResponseEntity.ok().body(roomPage);
    }

    @PostMapping("/Create")
    @Operation(summary = "Crear una nueva habitación")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDTO roomDTO) {
        Room createdRoom = roomService.createRoom(roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @DeleteMapping("/Delete/{id}")
    @Operation(summary = "Eliminar una Piso por su ID")
    public ResponseEntity<DeleteResponseDTO> deleteRoomById(@PathVariable Long id) {
        DeleteResponseDTO response = roomService.deleteRoomById(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/Update/{id}")
    @Operation(summary = "Actualizacion de Piso por su ID")
    public ResponseEntity<MessageDTO> updateRoomById(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        try {
            roomService.updateRoom(id, roomDTO);
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.OK.value());
            response.setMessage("Mesa actualizada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            MessageDTO response = new MessageDTO();
            response.setStatus((long) HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/Table/{id}")
    @Operation(summary = "Busqueda de mesas por piso")
    public ResponseEntity<ServiceTableDTO> getTableByidRoom(@PathVariable Long id) {
        ServiceTableDTO response = tableService.getTableByIdRoom(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
}
