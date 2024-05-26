package com.restaurant.system.backend_restaurant_system.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.system.backend_restaurant_system.persistence.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
    
}
